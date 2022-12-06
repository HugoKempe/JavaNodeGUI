package com.hugo.synth.controls;

import com.hugo.utilz.Constants;

import java.awt.*;

public class Button extends ButtonController {

    private boolean mouseOver, mousePressed;
    private boolean state;


    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update() {

    }

    public void draw(Graphics g) {

        if (mouseOver) {
            g.setColor(Constants.UI.Colors.MAIN_COLOR_FOCUS);
            g.fillRect(x, y, width, height);
        } else if (state) {
            g.setColor(Constants.UI.Colors.MAIN_COLOR);
            g.fillRect(x, y, width, height);
        }

        g.setColor(Constants.UI.Colors.MAIN_COLOR);
        g.drawRect(x, y, width, height);

        /*if (state) {
            drawState(x, y + height / 2, width, width, 0.8f, g);
        } else {
            drawState(x, y, width, width, 0.8f, g);
        }*/


        /*g.setColor(stateColor);
        g.fillRect((x) + (int) (stateWidth / 2), (y) + (int) (stateHeight / 2), width - (int)(stateWidth), height - (int)(stateHeight));*/
    }

/*    private void drawState(int x, int y, int width, int height, float scale, Graphics g) {
        int newWidth = (int) (width * scale);
        g.fillRect(x + (width - newWidth) / 2, y + (width - newWidth) / 2, newWidth, newWidth);
    }*/

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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void changeState() {
        state = !state;
    }
}
