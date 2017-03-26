package com.sapher.mysensors.message;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class MessageTest {

    private final String rawMessage = "4;7;1;0;0;43";

    @Test
    public void parseRawMessageTest() throws ParseException {
        Message message = Message.parse(rawMessage);
        Assert.assertEquals(message.getNodeId(), 4);
        Assert.assertEquals(message.getChildSensorId(), 7);
        Assert.assertEquals(message.getCommand().getValue(), Command.SET.getValue());
        Assert.assertEquals(message.isAck(), false);
        Assert.assertEquals(message.getType(), 0);
        Assert.assertEquals(message.getPayload(), "43");
    }

    @Test
    public void parseMessageToString() throws ParseException {
        Message message = Message.parse(rawMessage);
        Assert.assertEquals(rawMessage, message.toString());
    }
}
