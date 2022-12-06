package com.hugo.synth.controls;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class KnobController {

    protected int x, y, radius;
    protected String title;
    private Ellipse2D bounds;

    public KnobController(int x, int y, int radius, String title) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.title = title;
        createBounds();
    }

    private void createBounds() {
        bounds = new Ellipse2D.Double(x, y, radius, radius);
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Ellipse2D getBounds() {
        return bounds;
    }

    public void setBounds(Ellipse2D bounds) {
        this.bounds = bounds;
    }
}
