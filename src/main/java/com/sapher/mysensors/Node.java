package com.sapher.mysensors;

import com.sapher.mysensors.message.TypePresentation;
import com.sapher.mysensors.message.TypeReqSet;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private int id;
    private int childId;
    private int batteryLevel;
    private int time;
    private String sketchName;
    private String sketchVersion;
    private TypePresentation presentation;
    private String description;
    private Map<TypeReqSet, Float> variables = new HashMap<>();

    public Node() {
    }

    public Node(int nodeId, int childNodeId) {
        id = nodeId;
        childId = childNodeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSketchName() {
        return sketchName;
    }

    public void setSketchName(String sketchName) {
        this.sketchName = sketchName;
    }

    public String getSketchVersion() {
        return sketchVersion;
    }

    public void setSketchVersion(String sketchVersion) {
        this.sketchVersion = sketchVersion;
    }

    public TypePresentation getPresentation() {
        return presentation;
    }

    public void setPresentation(TypePresentation presentation) {
        this.presentation = presentation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVariable(TypeReqSet typeReqSet, float value) {
        variables.put(typeReqSet, value);
    }

    public float getVariable(TypeReqSet typeReqSet) {
        return variables.get(typeReqSet);
    }
}
