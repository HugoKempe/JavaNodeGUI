package com.hugo.synth.controls;

import com.hugo.utilz.Constants;

import java.awt.*;
import java.util.ArrayList;

import static com.hugo.synth.SynthManager.TILES;
import static com.hugo.synth.SynthManager.TILES_SIZE;

public class LoopRack {

    private int x;
    private int y;
    private int width;
    private int height;
    private ArrayList<Button> buttons;

    public LoopRack(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        buttons = new ArrayList<>();

        //initButtons();
    }

    private void initButtons() {
        for (int i = 0; i < 10; i++) {
            buttons.add(addButton(i, y, 2, 2));
        }
    }

    public void update() {
        for (Button b : buttons) {
            b.update();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Constants.UI.Colors.MAIN_COLOR);
        g.drawRect(x, y, width, height);

        for (Button b : buttons) {
            b.draw(g);
        }
    }

    private Button addButton(int x, int y, int width, int height) {
        int smallTileSize = TILES_SIZE / 4;
        Button b = new Button(this.x + (x * smallTileSize), this.y + (y * smallTileSize), width * smallTileSize, height * smallTileSize);
        return b;
    }
}
