package com.sapher.mysensors;

import com.sapher.mysensors.message.TypeReqSet;

public interface NodeListener {
    public void sensorDataReceived(Node sensorNode, TypeReqSet typeReq, float value);
}
