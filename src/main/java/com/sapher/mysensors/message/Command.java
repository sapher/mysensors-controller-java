package com.sapher.mysensors.message;

public enum Command {

    /**
     * 0 - Sent by a node when they present attached sensors. This is usually done in setup() at startup.
     */
    PRESENTATION(0),

    /**
     * 1 - This message is sent from or to a sensor when a sensor value should be updated
     */
    SET(1),

    /**
     * 2 - Requests a variable value (usually from an actuator destined for controller).
     */
    REQ(2),

    /**
     * 3 - This is a special internal message. See table below for the details
     */
    INTERNAL(3),

    /**
     * 4 - Used for OTA firmware updates
     */
    STREAM(4);

    private int value;

    Command(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Command fromValue(int value) {

        for (Command command : Command.values()) {
            if(command.getValue() == value) {
                return command;
            }
        }

        return null;
    }
}
