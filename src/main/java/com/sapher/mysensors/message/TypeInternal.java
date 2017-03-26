package com.sapher.mysensors.message;

public enum TypeInternal {

    /**
     * Use this to report the battery level (in percent 0-100).
     */
    I_BATTERY_LEVEL(0),

    /**
     * Sensors can request the current time from the Controller using this message. The time will be reported as the seconds since 1970
     */
    I_TIME(1),

    /**
     * Used to request gateway version from controller.
     */
    I_VERSION(2),

    /**
     * Use this to request a unique node id from the controller.
     */
    I_ID_REQUEST(3),

    /**
     * Id response back to node. Payload contains node id.
     */
    I_ID_RESPONSE(4),

    /**
     * Start/stop inclusion mode of the Controller (1=start, 0=stop).
     */
    I_INCLUSION_MODE(5),

    /**
     * Config request from node. Reply with (M)etric or (I)mperal back to sensor.
     */
    I_CONFIG(6),

    /**
     * When a sensor starts up, it broadcast a search request to all neighbor nodes. They reply with a I_FIND_PARENT_RESPONSE.
     */
    I_FIND_PARENT(7),

    /**
     * Reply message type to I_FIND_PARENT request.
     */
    I_FIND_PARENT_RESPONSE(8),

    /**
     * 	Sent by the gateway to the Controller to trace-log a message
     */
    I_LOG_MESSAGE(9),

    /**
     * A message that can be used to transfer child sensors (from EEPROM routing table) of a repeating node.
     */
    I_CHILDREN(10),

    /**
     * Optional sketch name that can be used to identify sensor in the Controller GUI
     */
    I_SKETCH_NAME(11),

    /**
     * Optional sketch version that can be reported to keep track of the version of sensor in the Controller GUI.
     */
    I_SKETCH_VERSION(12),

    /**
     * Used by OTA firmware updates. Request for node to reboot.
     */
    I_REBOOT(13),

    /**
     * Send by gateway to controller when startup is complete.
     */
    I_GATEWAY_READY(14),

    /**
     * Provides signing related preferences (first byte is preference version).
     */
    I_SIGNING_PRESENTATION(15),

    /**
     * Used between sensors when requesting nonce.
     */
    I_NONCE_REQUEST(16),

    /**
     * Used between sensors for nonce response.
     */
    I_NONCE_RESPONSE(17),

    /**
     * Heartbeat request
     */
    I_HEARTBEAT_REQUEST(18),

    /**
     * Presentation message
     */
    I_PRESENTATION(19),

    /**
     * Discover request
     */
    I_DISCOVER_REQUEST(20),

    /**
     * Discover response
     */
    I_DISCOVER_RESPONSE(21),

    /**
     * Heartbeat response
     */
    I_HEARTBEAT_RESPONSE(22),

    /**
     * Node is locked (reason in string-payload)
     */
    I_LOCKED(23),

    /**
     * Ping sent to node, payload incremental hop counter
     */
    I_PING(24),

    /**
     * In return to ping, sent back to sender, payload incremental hop counter
     */
    I_PONG(25),

    /**
     * Register request to GW
     */
    I_REGISTRATION_REQUEST(26),

    /**
     * Register response from GWI_DEBUG(28)	Debug message
     */
    I_REGISTRATION_RESPONSE(27),

    /**
     * Debug message
     */
    I_DEBUG(28);

    private int value;

    TypeInternal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypeInternal fromValue(int value) {

        for (TypeInternal typeInternal : TypeInternal.values()) {
            if(typeInternal.getValue() == value) {
                return typeInternal;
            }
        }

        return null;
    }
}
