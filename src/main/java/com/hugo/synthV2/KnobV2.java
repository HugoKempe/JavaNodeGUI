package com.hugo.synthV2;

import com.hugo.utilz.Constants;
import com.hugo.utilz.HelpMethods;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static com.hugo.synth.SynthManager.TILES_SIZE;

public class KnobV2 extends KnobControllerV2 implements SynthComponent {

    private boolean mouseOver, mousePressed;
    private int relativeMouseX, relativeMouseY;
    private double previousValue;
    private Color color;


    public KnobV2(int x, int y, int radius, String title, double value) {
        super(x, y, radius, title, value);
        previousValue = value;

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (mouseOver) {
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Constants.UI.Colors.MAIN_COLOR_FOCUS);
            g2.fillOval(x, y, radius, radius);
        }

        AffineTransform transform = new AffineTransform();
        double angle = Math.toRadians(HelpMethods.map(value, 0, 100, -135, 135));
        transform.rotate(angle, x + radius / 2,y + radius / 2);
        AffineTransform old = g2.getTransform();
        g2.setTransform(transform);

        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.drawLine(x + radius / 2, y + radius / 2, x + radius / 2, y);

        g2.setTransform(old);

        g2.transform(old);

        g2.setStroke(new BasicStroke(3));
        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.drawOval(x, y, radius, radius);

        g2.setStroke(new BasicStroke(6));
        g2.setColor(Constants.UI.Colors.MAIN_COLOR_FOCUS);
        int arcSize = (int) (radius * 1.5);
        g2.drawArc((int) (x - radius * 0.25), (int) (y - radius * 0.25), arcSize, arcSize, -135, -270);
        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.drawArc((int) (x - radius * 0.25), (int) (y - radius * 0.25), arcSize, arcSize, -135, (int) HelpMethods.map(value, 0, 100, 0, -270));
        HelpMethods.drawCenteredString(g, String.valueOf((float)value),
                new Rectangle(x, (int) (y + ((TILES_SIZE / 8) + TILES_SIZE / 2) + TILES_SIZE / 16), radius, (int) (TILES_SIZE / 4)),
                Constants.UI.Fonts.BRUSH_SCRIPT_MT, Constants.UI.Colors.MAIN_COLOR);


        HelpMethods.drawCenteredString(g, title,
                new Rectangle(x, (int) (y - radius), radius, (int) (TILES_SIZE / 4)),
                Constants.UI.Fonts.BRUSH_SCRIPT_MT_LARGE, Constants.UI.Colors.MAIN_COLOR);
    }

    @Override
    public boolean isMouseOver() {
        return mouseOver;
    }

    @Override
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    @Override
    public boolean isMousePressed() {
        return mousePressed;
    }

    @Override
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
        previousValue = value;
    }

    @Override
    public void setRelativeMousePos(int mX, int mY) {
        relativeMouseX = mX;
        relativeMouseY = mY;
    }

    @Override
    public void changeValue(MouseEvent e) {
        value = HelpMethods.limit((previousValue + (relativeMouseY - e.getY()) *  0.5), 0, 100);
    }

    @Override
    public String getTitle() {
        return title;
    }

/*    @Override
    public void stateChanged(ChangeEvent e) {
        KnobV2 k = (KnobV2) e.getSource();
        if (k.getValue() != k.previousValue) {
            System.out.println(k.getValue());
        }
    }*/
}
