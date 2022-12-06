package com.hugo.nodes;

import java.awt.*;

public class TestNode extends BaseNode {

    private boolean mouseOver, mousePressed;
    private boolean muted;
    private Color color;

    public TestNode(int x, int y, int width, int height) {
        super(x, y, width, height);
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

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
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
}
