package com.sapher.mysensors;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.sapher.mysensors.message.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller implements SerialPortDataListener {

    // Constants
    private static final String TTY_GATEWAY_PORT = "/dev/ttyGateway";

    // Physical
    private SerialPort serialPort;

    // Data
    private Map<String, Node> nodes = new HashMap<>();
    private List<NodeListener> nodeListeners = new ArrayList<>();

    public Controller() {
        serialPort = SerialPort.getCommPort(TTY_GATEWAY_PORT);
    }

    public Controller(String ttyGatewayPort) {
        serialPort = SerialPort.getCommPort(ttyGatewayPort);
    }

    public void start() {
        serialPort.openPort();
        serialPort.addDataListener(this);
    }

    public void stop() {
        serialPort.closePort();
        serialPort.removeDataListener();
    }

    public void addNodeListener(NodeListener nodeListener) {
        nodeListeners.add(nodeListener);
    }

    public void removeNodeListener(NodeListener nodeListener) {
        nodeListeners.remove(nodeListener);
    }

    @Override
    public int getListeningEvents() {
        return  SerialPort.LISTENING_EVENT_DATA_AVAILABLE |
                //SerialPort.LISTENING_EVENT_DATA_RECEIVED |
                SerialPort.LISTENING_EVENT_DATA_WRITTEN;
    }

    private String[] parseRawMessage(String rawMessage) {

        // Trim last jump line
        int strLength = rawMessage.length();
        if(rawMessage.charAt(strLength - 1) == '\n') {
            rawMessage = rawMessage.substring(0, strLength - 1);
        }

        // Split data to array
        return rawMessage.split("\n");
    }

    @Override
    public void serialEvent(SerialPortEvent event) {

        switch (event.getEventType()) {
            case SerialPort.LISTENING_EVENT_DATA_AVAILABLE:
                //LOGGER.info("listening event data available");

                // Transform raw data
                byte[] rawData = new byte[serialPort.bytesAvailable()];
                int numRead = serialPort.readBytes(rawData, rawData.length);
                String data = new String(rawData);

                // Split data to array
                String[] lines = parseRawMessage(data);

                // Notify new data
                for (String line: lines) {

                    if(line == null) continue;
                    if(line.isEmpty()) continue;

                    // TODO: 07/03/2017 should throw parse exception
                    Message message = null;
                    try {
                        message = Message.parse(line);
                    } catch (ParseException e) {

                        continue;
                    }

                    // Some data
                    String payload = message.getPayload();
                    int nodeId = message.getNodeId();
                    int childSensorId = message.getChildSensorId();

                    // Get node
                    Node node = getNode(nodeId, childSensorId);

                    // Route message
                    switch (message.getCommand()) {

                        // Presentation
                        case PRESENTATION:
                            TypePresentation typePresentation = TypePresentation.fromValue(message.getType());
                            node.setPresentation(typePresentation);
                            node.setDescription(payload);
                            break;

                        // Interval
                        case INTERNAL:

                            TypeInternal typeInternal = TypeInternal.fromValue(message.getType());

                            switch (typeInternal) {

                                // Battery level
                                case I_BATTERY_LEVEL:
                                    node.setBatteryLevel(Integer.parseInt(payload));
                                    break;

                                // Time
                                case I_TIME:
                                    if(payload != null) {
                                        node.setTime(Integer.parseInt(payload));
                                    }
                                    break;

                                // ID request
                                case I_ID_REQUEST:
                                    // fixme can't work, id should be setted
                                    //broadcastIdResponse(nodeId);
                                    break;

                                // Config
                                case I_CONFIG:
                                    // return (I)mperial or (M)etric
                                    break;

                                // Sketch name
                                case I_SKETCH_NAME:
                                    node.setSketchName(payload);
                                    break;


                                // Sketch version
                                case I_SKETCH_VERSION:
                                    node.setSketchVersion(payload);
                                    break;
                            }

                            break;

                        // Set
                        case SET:

                            // Parse incoming data
                            float value = Float.parseFloat(payload);

                            TypeReqSet typeReq = TypeReqSet.fromValue(message.getType());
                            node.setVariable(typeReq, value);

                            // todo notify listener new sensor data arrived
                            for (NodeListener nodeListener : nodeListeners) {
                                nodeListener.sensorDataReceived(node, typeReq, value);
                            }

                            break;

                        // Req
                        case REQ:
                            TypeReqSet typeSet = TypeReqSet.fromValue(message.getType());
                            float reqValue = node.getVariable(typeSet);
                            sendRequestResponse(nodeId, childSensorId, typeSet, reqValue);
                            break;

                        // Stream
                        case STREAM:
                            // Not handled
                            break;

                        default:break;
                    }

                    //LOGGER.warning(message.toExplicitString());
                }

                break;

            /**case SerialPort.LISTENING_EVENT_DATA_RECEIVED:
                LOGGER.info("listening event data received");
                break;**/

            case SerialPort.LISTENING_EVENT_DATA_WRITTEN:
                //LOGGER.info("listening event data written");
                break;

            default:break;
        }
    }

    /**
     * Broadcast ID Response to ID Request
     * @param nodeId node id
     */
    private void broadcastIdResponse(int nodeId) {
        Message message = new Message(255, 255, Command.INTERNAL, false, TypeInternal.I_ID_RESPONSE, String.format("%d", nodeId));
        sendMessage(message);
    }

    /**
     * Send response to a request send by a node
     * @param nodeId node id
     * @param childNodeId child sensor id
     * @param typeSet
     * @param value value to send
     */
    private void sendRequestResponse(int nodeId, int childNodeId, TypeReqSet typeSet, float value) {
        Message message = new Message(nodeId, childNodeId, Command.REQ, false, typeSet, String.format("%f", value));
        sendMessage(message);
    }

    /**
     * Send message
     * @param message message to send
     */
    private void sendMessage(Message message) {
        String rawMessage = message.toString();
        byte[] bytes = rawMessage.getBytes();
        serialPort.writeBytes(bytes, rawMessage.length());
    }

    /**
     * Return a node, create if not exist
     * @param nodeId node id
     * @param childNodeId child sensor id
     * @return node
     */
    private Node getNode(int nodeId, int childNodeId) {
        String key = String.format("%d_%d", nodeId, childNodeId);
        return nodes.computeIfAbsent(key, k -> new Node(nodeId, childNodeId));
    }

    /**
     * Remove a node from nodes
     * @param nodeId node id
     * @param childNodeId child sensor id
     */
    private void removeNode(int nodeId, int childNodeId) {
        String key = String.format("%d_%d", nodeId, childNodeId);
        nodes.remove(key);
    }

    /**
     * Return all nodes
     * @return nodes
     */
    public Map<String, Node> getNodes() {
        return nodes;
    }

    /**
     * Return all sub nodes of a node
     * @param node node
     * @return nodes
     */
    public Map<String, Node> getSubNodes(Node node) {

        return nodes
                .entrySet()
                .stream()
                .filter(e -> e.getKey().startsWith(node.getId() + "_"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Return a new node id that is not in use
     * @return node id
     */
    /*public int getFreeNodeId() {
        throw new NotImplementedException();
    }*/
}
