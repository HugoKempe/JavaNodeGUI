package com.hugo.synthV2;

import com.hugo.appstatesTODO.SynthStateV2;
import com.hugo.synth.controls.Knob;
import com.hugo.utilz.Constants;
import com.hugo.utilz.HelpMethods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

import static com.hugo.main.App.APP_WIDTH;
import static com.hugo.main.App.APP_HEIGHT;
import static com.hugo.main.App.SYNTH_HEIGHT;
import static com.hugo.main.App.SYNTH_WIDTH;
import static com.hugo.synth.SynthManager.TILES;
import static com.hugo.synth.SynthManager.TILES_SIZE;

public class SynthManagerV2 {


    private SynthStateV2 synthStateV2;
    private int x;
    private int y;
    private int width;
    private int height;
    private int tiles;
    private int tileSize;
    private int tilesInWidth = 40;
    private int tilesInHeight = 30;

    private double attackMS, decayMS, sustainDB, releaseMS;

    private ArrayList<SynthComponent> knobs;

    public SynthManagerV2(SynthStateV2 synthStateV2) {
        this.synthStateV2 = synthStateV2;

        tiles = 40;
        tileSize = (int) (APP_WIDTH * 0.9) / tiles;
        width = tileSize * tilesInWidth;
        height = tileSize * tilesInHeight;
        x = (APP_WIDTH - width) / 2;
        y = (APP_HEIGHT - height) / 2;




        System.out.println(width / tiles);
        knobs = new ArrayList<>();
        //knobs.add(createKnob(2, 2, "Gain"));
        knobs.add(new KnobV2(this.x + (tileSize * 23) - 20, this.y + (tileSize * 28) - 20, 40, "Attack", 50.00));
        knobs.add(new KnobV2(this.x + (tileSize * 26) - 20, this.y + (tileSize * 28) - 20, 40, "Decay", 50.00));
        knobs.add(new KnobV2(this.x + (tileSize * 29) - 20, this.y + (tileSize * 28) - 20, 40, "Sustain", 50.00));
        knobs.add(new KnobV2(this.x + (tileSize * 32) - 20, this.y + (tileSize * 28) - 20, 40, "Release", 50.00));
        initADSR();
    }

    private KnobV2 createKnob(int x, int y, String title) {
        return new KnobV2(this.x + (tileSize * x) - 20, this.y + (tileSize * y) - 20, 40, title, 50.00);
    }

    public void update() {
        for (SynthComponent c : knobs) {
            c.update();
        }
    }

    public void draw(Graphics g) {
        drawBackground(g);
        drawADSR(g);
        for (SynthComponent c : knobs) {
            c.draw(g);
        }
    }

    private void initADSR() {
        for (SynthComponent c : knobs) {
            if (Objects.equals(c.getTitle(), "Attack")) {
                attackMS = c.getValue();
            }
            if (Objects.equals(c.getTitle(), "Decay")) {
                decayMS = c.getValue();
            }
            if (Objects.equals(c.getTitle(), "Sustain")) {
                sustainDB = c.getValue();
            }
            if (Objects.equals(c.getTitle(), "Release")) {
                releaseMS = c.getValue();
            }
        }
    }

    private void drawADSR(Graphics g) {
        int xPos = this.x + (tileSize * 20);
        int yPos = this.y + (tileSize * 20);
        int boxWidth = (tileSize * 15);
        int boxHeight = (tileSize * 5);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(xPos, yPos, boxWidth, boxHeight);

        int attackPos = (int)(HelpMethods.map(attackMS, 0.0, 100.0, xPos, xPos + boxWidth / 3.0));
        g2.drawLine(xPos, yPos + boxHeight, attackPos, yPos + tileSize);
        int decayPos = (int)(HelpMethods.map(decayMS, 0.0, 100.0, attackPos, attackPos + boxWidth / 3.0));
        int sustainPos = (int)(HelpMethods.map(-sustainDB, 0.0, 100.0, yPos + boxHeight, yPos + boxHeight + boxHeight - tileSize));
        g2.drawLine(attackPos, yPos + tileSize, decayPos, sustainPos);
        int releasePos = (int)(HelpMethods.map(releaseMS, 0.0, 100.0, decayPos, decayPos + boxWidth / 3.0));
        g2.drawLine(decayPos, sustainPos, releasePos, yPos + boxHeight);

    }

    public void mousePressed(MouseEvent e) {
        for (SynthComponent c : knobs) {
            if (c.isIn(e, c)) {
                c.setMousePressed(true);
                c.setRelativeMousePos(e.getX(), e.getY());
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("A: " + attackMS + " D: " + decayMS + " S: " + sustainDB + " R: " + releaseMS);
        for (SynthComponent c : knobs) {
            c.setMousePressed(false);
            c.resetBools();
        }
    }

    public void mouseMoved(MouseEvent e) {
        for (SynthComponent c : knobs) {
            c.setMouseOver(false);
            if (c.isIn(e, c)) {
                c.setMouseOver(true);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        for (SynthComponent c : knobs) {
            if (c.isMousePressed()) {
                c.changeValue(e);
                if (Objects.equals(c.getTitle(), "Attack")) {
                    attackMS = c.getValue();
                }
                if (Objects.equals(c.getTitle(), "Decay")) {
                    decayMS = c.getValue();
                }
                if (Objects.equals(c.getTitle(), "Sustain")) {
                    sustainDB = c.getValue();
                }
                if (Objects.equals(c.getTitle(), "Release")) {
                    releaseMS = c.getValue();
                }
            }
        }
    }

    private void drawBackground(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Constants.UI.Colors.BACKGROUND_COLOR);
        g2.fillRect(x, y, width, height);

        g2.setColor(Constants.UI.Colors.LINE_COLOR_2);
        g2.setStroke(new BasicStroke(2));
        for (int row = 1; row < tilesInHeight; row++) {
            g2.drawLine(x, y + row * tileSize, x + width, y + row * tileSize);
        }
        for (int col = 1; col < tilesInWidth; col++) {
            g2.drawLine(x + (col * tileSize), y, (x + col * tileSize), y + height);
        }

        g2.setColor(Constants.UI.Colors.LINE_COLOR_1);
        g2.setStroke(new BasicStroke(3));
        for (int row = 1; row < tilesInHeight / 5; row++) {
            g2.drawLine(x, y + row * (tileSize * 5), x + width, y + row * (tileSize * 5));
        }
        for (int col = 1; col < tilesInWidth / 5; col++) {
            g2.drawLine(x + (col * (tileSize * 5)), y, (x + col * (tileSize * 5)), y + height);
        }

        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x, y, width, height);
    }
}
