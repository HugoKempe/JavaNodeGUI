package com.hugo.nodesOld;

import java.awt.*;

public class BaseNodeIO {

    protected int x, y, width, height;
    protected Rectangle bounds;
    protected BaseNode parentNode;

    public BaseNodeIO(int x, int y, int width, int height, BaseNode parentNode) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.parentNode = parentNode;
        createBounds();
    }

    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
