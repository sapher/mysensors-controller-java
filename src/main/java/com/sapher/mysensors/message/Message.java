package com.sapher.mysensors.message;

import java.text.ParseException;

public class Message {

    private int nodeId;
    private int childSensorId;
    private Command command;
    private boolean ack;
    private int type;
    private String payload;

    public Command getCommand() {
        return command;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getChildSensorId() {
        return childSensorId;
    }

    public void setChildSensorId(int childSensorId) {
        this.childSensorId = childSensorId;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Message() {

    }

    public Message(int nodeId, int childSensorId, Command command, boolean ack, int type, String payload) {
        this.nodeId = nodeId;
        this.childSensorId = childSensorId;
        this.command = command;
        this.ack = ack;
        this.type = type;
        this.payload = payload;
    }

    public Message(int nodeId, int childSensorId, Command command, boolean ack, TypeInternal type, String payload) {
        this(nodeId, childSensorId, command, ack, type.getValue(), payload);
    }

    public Message(int nodeId, int childSensorId, Command command, boolean ack, TypePresentation type, String payload) {
        this(nodeId, childSensorId, command, ack, type.getValue(), payload);
    }

    public Message(int nodeId, int childSensorId, Command command, boolean ack, TypeReqSet type, String payload) {
        this(nodeId, childSensorId, command, ack, type.getValue(), payload);
    }

    public static Message parse(String rawMessage) throws ParseException {
        try {
            String[] parts = rawMessage.split(";");

            // raw data
            int nodeId = Integer.parseInt(parts[0]);
            int childNodeId = Integer.parseInt(parts[1]);
            int rawCmd = Integer.parseInt(parts[2]);
            boolean rawAck = Boolean.parseBoolean(parts[3]);
            int type = Integer.parseInt(parts[4]);

            // build message
            Command command = Command.fromValue(rawCmd);

            Message message = new Message(nodeId, childNodeId, command, rawAck, type, null);

            // Payload can exist or not
            if(parts.length == 6) {
                message.setPayload(parts[5]);
            }

            return message;
        }
        catch (Exception e) {
            throw new ParseException("Unable to parse raw message", 0);
        }
    }

    @Override
    public String toString() {
        return String.format("%d;%d;%d;%d;%d;%s", nodeId, childSensorId, command.getValue(), (ack) ? 1: 0, type, (payload == null) ? "" : payload);
    }

    public String toExplicitString() {

        String Stype = "";

        switch (command) {
            case PRESENTATION:
                Stype = TypePresentation.fromValue(type).name();
                break;

            case SET:
                Stype = TypeReqSet.fromValue(type).name();
                break;

            case REQ:
                Stype = TypeReqSet.fromValue(type).name();
                break;

            case INTERNAL:
                Stype = TypeInternal.fromValue(type).name();
                break;
        }

        return String.format("%d - %d - %s - %s - %s", nodeId, childSensorId, command.name(), Stype, payload);
    }
}
