package com.hugo.synthV2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class KnobControllerV2 {

    protected int x, y, radius;
    protected double value;
    protected String title;
    protected Ellipse2D bounds;


    public KnobControllerV2(int x, int y, int radius, String title, double value) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.title = title;
        this.value = value;
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

    public boolean isIn(MouseEvent e, SynthComponent s) {
        return s.getBounds().contains(e.getX(), e.getY());
    }
}
