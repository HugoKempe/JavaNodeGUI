package com.hugo.synthV2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public interface SynthComponent {
    public void update();
    public void draw(Graphics g);

    public boolean isMouseOver();
    public void setMouseOver(boolean mouseOver);
    public boolean isMousePressed();
    public void setMousePressed(boolean mousePressed);

    public double getValue();
    public void resetBools();
    public void setRelativeMousePos(int mX, int mY);
    public void changeValue(MouseEvent e);
    public String getTitle();

    public Ellipse2D getBounds();
    default boolean isIn(MouseEvent e, SynthComponent c) {
        return c.getBounds().contains(e.getX(), e.getY());
    }
}
