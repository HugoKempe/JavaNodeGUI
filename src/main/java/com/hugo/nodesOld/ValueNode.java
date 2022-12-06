package com.hugo.nodesOld;

public class ValueNode {

    private double value;

    public ValueNode(int value) {
        this.value = value;
    }

    public ValueNode() {

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
