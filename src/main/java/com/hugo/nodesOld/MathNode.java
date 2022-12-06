package com.hugo.nodesOld;

public class MathNode {

    public static final int ADD = 0;
    public static final int SUBTRACT = 1;
    public static final int DIVIDE = 2;
    public static final int MULTIPLY = 3;

    private int type;

    private double input1;
    private double input2;
    private double output;

    public MathNode(int type) {
        this.type = type;
    }

    public MathNode() {

    }

    public void update() {

    }

    private double doOperation() {
        double sum = 0;
        switch (type) {
            case ADD:
                sum = input1 + input2;
                break;
            case SUBTRACT:
                sum = input1 - input2;
                break;
            case DIVIDE:
                sum = input1 / input2;
                break;
            case MULTIPLY:
                sum = input1 * input2;
                break;
            default:
                break;
        }

        return sum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getInput1() {
        return input1;
    }

    public void setInput1(double input1) {
        this.input1 = input1;
    }

    public double getInput2() {
        return input2;
    }

    public void setInput2(double input2) {
        this.input2 = input2;
    }

    public double getOutput() {
        output = doOperation();
        return output;
    }
}
