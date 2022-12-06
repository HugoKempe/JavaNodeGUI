package com.hugo.synth.controls;

import com.hugo.utilz.Constants;
import com.hugo.utilz.HelpMethods;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static com.hugo.synth.SynthManager.TILES_SIZE;

public class Knob extends KnobController {

    private boolean mouseOver, mousePressed;
    private Color color;
    private double value;
    private double previousValue;
    private int relativeMouseX, relativeMouseY;
    private Font font;

    public Knob(int x, int y, int radius, String title) {
        super(x, y, radius, title);
        value = 50;
        previousValue = 50;
    }

    public void update() {
        color = Constants.UI.Colors.MEDIUM_DARK;

        if (mouseOver)
            color = Constants.UI.Colors.MEDIUM_LIGHT;
        if (mousePressed)
            color = Constants.UI.Colors.LIGHT;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (mouseOver) {
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Constants.UI.Colors.MAIN_COLOR_FOCUS);
            g2.fillOval(x, y, radius, radius);
        }

        g2.setStroke(new BasicStroke(3));
        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.drawOval(x, y, radius, radius);

        AffineTransform transform = new AffineTransform();
        double angle = Math.toRadians(HelpMethods.map(value, 0, 100, -135, 135));
        transform.rotate(angle, x + radius / 2,y + radius / 2);
        AffineTransform old = g2.getTransform();
        g2.setTransform(transform);

        /*g2.setColor(Constants.UI.Colors.MEDIUM_DARK);
        g2.fillOval(x, y, radius, radius);*/

        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.drawLine(x + radius / 2, y + radius / 2, x + radius / 2, y);



        g2.setTransform(old);

        g2.transform(old);

        //g2.drawArc(x, y, (int) (radius * 1), (int) (radius * 1), 360-135, (int) HelpMethods.map(value, 0, 100, 0, -270));
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Constants.UI.Colors.MAIN_COLOR_FOCUS);
        int arcSize = (int) (radius * 1.5);
        g2.drawArc((int) (x - radius * 0.25), (int) (y - radius * 0.25), arcSize, arcSize, -135, -270);
        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.drawArc((int) (x - radius * 0.25), (int) (y - radius * 0.25), arcSize, arcSize, -135, (int) HelpMethods.map(value, 0, 100, 0, -270));
        /*int lY = (TILES_SIZE / TILES) * 10;
        g2.drawLine(x, y + lY, x + TILES_SIZE, y + lY);*/

        HelpMethods.drawCenteredString(g, String.valueOf((float)value),
                new Rectangle(x, (int) (y + ((TILES_SIZE / 8) + TILES_SIZE / 2) + TILES_SIZE / 16), radius, (int) (TILES_SIZE / 4)),
                Constants.UI.Fonts.BRUSH_SCRIPT_MT, Constants.UI.Colors.MAIN_COLOR);


        HelpMethods.drawCenteredString(g, title,
                new Rectangle(x, (int) (y - radius), radius, (int) (TILES_SIZE / 4)),
                Constants.UI.Fonts.BRUSH_SCRIPT_MT_LARGE, Constants.UI.Colors.MAIN_COLOR);


        //g2.dispose();
    }

    public void setRelativeMousePos(int mX, int mY) {
        relativeMouseX = mX;
        relativeMouseY = mY;
    }

    public void changeValue(int mX, int mY) {
        value = HelpMethods.limit((previousValue + (relativeMouseY - mY) *  0.5), 0, 100);
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
        previousValue = value;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
