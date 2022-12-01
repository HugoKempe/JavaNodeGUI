package com.hugo.nodes;

import java.awt.*;

public class NodeInput extends BaseNodeIO {

    private boolean mouseOver, mousePressed;
    private boolean muted;
    private Color color;
    private double value;
    public boolean hasConnection = false;

    public NodeInput(int x, int y, int width, int height, BaseNode parentNode) {
        super(x, y, width, height, parentNode);
    }

    public void update() {
        color = Color.red;
        if (mouseOver) {
            color = Color.BLUE;
        }
        if (mousePressed) {
            color = Color.green;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, bounds.width, bounds.height);
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
