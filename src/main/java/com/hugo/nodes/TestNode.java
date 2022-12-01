package com.hugo.nodes;

import java.awt.*;

import static com.hugo.main.App.APP_WIDTH;
import static com.hugo.main.App.APP_HEIGHT;

public class TestNode extends BaseNode {

    private NodeInput[] nodeInputs;
    private NodeOutput[] nodeOutputs;

    private boolean mouseOver, mousePressed;
    private boolean muted;
    private Color color;

    public TestNode(int x, int y, int width, int height) {
        super(x, y, width, height);
        color = Color.red;
        initIO();
    }

    private void initIO() {
        nodeInputs = new NodeInput[1];
        nodeInputs[0] = new NodeInput(x, y - 10 + height / 2, 20, 20, this);

        nodeOutputs = new NodeOutput[1];
        nodeOutputs[0] = new NodeOutput(x + width - 20, y - 10 + height / 2, 20, 20, this);
    }

    public void update() {
        color = Color.red;
        if (mouseOver) {
            color = Color.BLUE;
        }
        if (mousePressed) {
            color = Color.green;
        }

        nodeInputs[0].update();
        nodeOutputs[0].update();

        nodeOutputs[0].setValue(nodeInputs[0].getValue() / 2);
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        float scale = 0.9f;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(10));
        g2.drawRect(x, y, width, height);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, width, height);
        nodeInputs[0].draw(g);
        nodeOutputs[0].draw(g);
    }

    private void renderNode(Graphics g) {
        double scale = 0.9;



        g.fillRoundRect((int) (x * scale), (int) (y * scale), (int) (width * scale), (int) (height * scale), (int) (10 * scale), (int) (10 * scale));
    }

    private void drawCenterRect(Graphics g, int x, int y, int width, int height, int arc) {
        g.fillRoundRect(x - width / 2, y - height / 2, width, height, arc, arc);
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public NodeInput[] getNodeInputs() {
        return nodeInputs;
    }

    public NodeOutput[] getNodeOutputs() {
        return nodeOutputs;
    }

    public void setInputValue(int index, double value) {
        nodeInputs[index].setValue(value);
        //System.out.println(nodeInputs[index].getValue());
    }

    public double getInputValue(int index) {
        return nodeInputs[index].getValue();
    }

    public void setOutputValue(int index, double value) {
        nodeOutputs[index].setValue(value);
        //System.out.println(nodeInputs[index].getValue());
    }

    public double getOutputValue(int index) {
        return nodeOutputs[index].getValue();
    }
}
