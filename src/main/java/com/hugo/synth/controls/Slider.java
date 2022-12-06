package com.hugo.synth.controls;

import java.awt.*;

public class Slider extends SliderController {

    public Slider(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}
