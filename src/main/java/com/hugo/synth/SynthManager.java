package com.hugo.synth;

import com.hugo.appstatesTODO.SynthState;
import com.hugo.synth.controls.Button;
import com.hugo.synth.controls.ButtonController;
import com.hugo.synth.controls.KnobController;
import com.hugo.synth.controls.Knob;
import com.hugo.synth.controls.LoopRack;
import com.hugo.synth.controls.Slider;
import com.hugo.utilz.HelpMethods;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.hugo.main.App.APP_HEIGHT;
import static com.hugo.main.App.APP_WIDTH;
import static com.hugo.main.App.SYNTH_WIDTH;
import static com.hugo.main.App.SYNTH_HEIGHT;

public class SynthManager {

    private SynthState synthState;
    private Synth synth;
    private Knob volumeKnob;
    private Knob rateKnob;

    private Knob attackKnob;
    private Knob holdKnob;
    private Knob decayKnob;
    private Knob sustainKnob;
    private Knob releaseKnob;

    private ArrayList<Knob> knobs;
    private Button button;
    private LoopRack loopRack;
    private Slider slider;

    public final static int TILES = 16;
    public final static int TILES_SIZE = SYNTH_WIDTH / TILES;

    public SynthManager(SynthState synthState) {
        this.synthState = synthState;
        initSynth();

        synth.setFreq(HelpMethods.map(volumeKnob.getValue(), 0, 100, 0.0, 0.2));
        //synth.getSynthEngine().startSynth();
    }

    private void initSynth() {
        //synth = new Synth(APP_WIDTH / 2 - 400, APP_HEIGHT / 2 - 300, 800, 600);
        synth = new Synth((APP_WIDTH - SYNTH_WIDTH) / 2, (APP_HEIGHT - SYNTH_HEIGHT) / 2, SYNTH_WIDTH, SYNTH_HEIGHT);
        //synth.getSynthEngine().getOscillator().amplitude.set(0.01);
        loopRack = new LoopRack(synth.getX() + TILES_SIZE * 2, synth.getY() + TILES_SIZE * 10, TILES_SIZE * 12, TILES_SIZE);
        createKnobs();
    }

    private void initADHSR() {
        attackKnob = new Knob(synth.getX() + (TILES_SIZE * 2) - 20, synth.getY() + (TILES_SIZE * 5) - 20, 40, "A");
        holdKnob = new Knob(synth.getX() + (TILES_SIZE * 4) - 20, synth.getY() + (TILES_SIZE * 5) - 20, 40, "H");
        decayKnob = new Knob(synth.getX() + (TILES_SIZE * 6) - 20, synth.getY() + (TILES_SIZE * 5) - 20, 40, "D");
        sustainKnob = new Knob(synth.getX() + (TILES_SIZE * 8) - 20, synth.getY() + (TILES_SIZE * 5) - 20, 40, "S");
        releaseKnob = new Knob(synth.getX() + (TILES_SIZE * 10) - 20, synth.getY() + (TILES_SIZE * 5) - 20, 40, "R");
    }

    private void createKnobs() {
        //button = new Button(synth.getX() + (TILES_SIZE * 5), synth.getY() + (TILES_SIZE * 2), TILES_SIZE, TILES_SIZE);
        button = addButton(16, 2, 2, 2);
        volumeKnob = new Knob(synth.getX() + (TILES_SIZE * 2) - 20, synth.getY() + (TILES_SIZE * 2) - 20, 40, "Gain");
        rateKnob = new Knob(synth.getX() + (TILES_SIZE * 4) - 20, synth.getY() + (TILES_SIZE * 2) - 20, 40, "Rate");


        knobs = new ArrayList<>();
        /*knobs.add(new Knob(synth.getX() + 120, synth.getY() + 50,  50));
        knobs.add(new Knob(synth.getX() + 190, synth.getY() + 50, 50));
        knobs.add(new Knob(synth.getX() + 260, synth.getY() + 50, 50));*/
        //knobs.add(new Knob(synth.getX() + 500, synth.getY() + 500, 50));
        //Random random = new Random();
        /*for (int i = 1; i < 5; i++) {
            knobs.add(new Knob(synth.getX() + (TILES_SIZE * i * 2) - 20, synth.getY() + (TILES_SIZE * 4) - 20, 40, String.valueOf(i)));
        }*/
        initADHSR();
        knobs.add(rateKnob);

        knobs.add(attackKnob);
        knobs.add(holdKnob);
        knobs.add(decayKnob);
        knobs.add(sustainKnob);
        knobs.add(releaseKnob);

/*        for (Knob k : knobs) {
            double val = random.nextInt(200) / 2.0;
            k.setValue(val);
        }*/
    }

    private Button addButton(int x, int y, int width, int height) {
        int smallTileSize = TILES_SIZE / 4;
        Button b = new Button(synth.getX() + (x * smallTileSize), synth.getY() + (y * smallTileSize), width * smallTileSize, height * smallTileSize);
        return b;
    }

    public void update() {
        synth.update();
        loopRack.update();
        volumeKnob.update();
        button.update();
        for (Knob k : knobs) {
            k.update();
        }
    }

    public void draw(Graphics g) {
        synth.draw(g);
        loopRack.draw(g);
        volumeKnob.draw(g);
        button.draw(g);
        for (Knob k : knobs) {
            k.draw(g);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isInKnob(e, volumeKnob)) {
            volumeKnob.setMousePressed(true);
            volumeKnob.setRelativeMousePos(e.getX(), e.getY());
        }
        for (Knob k : knobs) {
            if (isInKnob(e, k)) {
                k.setMousePressed(true);
                k.setRelativeMousePos(e.getX(), e.getY());
            }
        }
        if (isIn(e, button)) {
            button.setMousePressed(true);
            button.changeState();
        }

        if (isIn(e, synth))
            synth.setMousePressed(true);

    }

    public void mouseReleased(MouseEvent e) {
        if (isInKnob(e, volumeKnob)) {
            volumeKnob.setMousePressed(false);
        }
        for (Knob k : knobs) {
            if (isInKnob(e, k)) {
                k.setMousePressed(false);
            }
        }
        if (isIn(e, button)) {
            button.setMousePressed(false);
        }

        if (isIn(e, synth))
            synth.setMousePressed(false);

        volumeKnob.resetBools();
        synth.resetBools();
        button.resetBools();
        for (Knob k : knobs) {
            k.resetBools();
        }
    }

    public void mouseMoved(MouseEvent e) {
        synth.setMouseOver(false);
        volumeKnob.setMouseOver(false);
        button.setMouseOver(false);
        for (Knob k : knobs) {
            k.setMouseOver(false);
        }

        if (isInKnob(e, volumeKnob)) {
            volumeKnob.setMouseOver(true);
        }
        for (Knob k : knobs) {
            if (isInKnob(e, k)) {
                k.setMouseOver(true);
            }
        }
        if (isIn(e, button)) {
            button.setMouseOver(true);
        }
        if (isIn(e, synth)) {
            synth.setMouseOver(true);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (volumeKnob.isMousePressed()) {
            volumeKnob.changeValue(e.getX(), e.getY());
            synth.setFreq(HelpMethods.map(volumeKnob.getValue(), 0, 100, 0.0, 0.2));
        }
        if (rateKnob.isMousePressed()) {
            rateKnob.changeValue(e.getX(), e.getY());
            synth.getSynthEngine().setBpm((int)(HelpMethods.map(rateKnob.getValue(), 0, 100, 100, 1000)) );
        }
        if (attackKnob.isMousePressed()) {
            attackKnob.changeValue(e.getX(), e.getY());
            synth.getSynthEngine().setAttack(HelpMethods.map(attackKnob.getValue(), 0, 100, 0.0, 1.0));
        }
        if (decayKnob.isMousePressed()) {
            decayKnob.changeValue(e.getX(), e.getY());
            synth.getSynthEngine().setDecay(HelpMethods.map(decayKnob.getValue(), 0, 100, 0.0, 1.0));
        }
        if (holdKnob.isMousePressed()) {
            holdKnob.changeValue(e.getX(), e.getY());
            synth.getSynthEngine().setHold(HelpMethods.map(holdKnob.getValue(), 0, 100, 0.0, 1.0));
        }
        if (sustainKnob.isMousePressed()) {
            sustainKnob.changeValue(e.getX(), e.getY());
            synth.getSynthEngine().setSustain(HelpMethods.map(sustainKnob.getValue(), 0, 100, 0.0, 1.0));
        }
        if (releaseKnob.isMousePressed()) {
            releaseKnob.changeValue(e.getX(), e.getY());
            synth.getSynthEngine().setRelease(HelpMethods.map(releaseKnob.getValue(), 0, 100, 0.0, 1.0));
        }

        for (Knob k : knobs) {
            if (k.isMousePressed()) {
                k.changeValue(e.getX(), e.getY());
            }
        }
    }

    private boolean isIn(MouseEvent e, BaseSynth bs) {
        return bs.getBounds().contains(e.getX(), e.getY());
    }

    private boolean isIn(MouseEvent e, ButtonController b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    private boolean isInKnob(MouseEvent e, KnobController k) {
        return k.getBounds().contains(e.getX(), e.getY());
    }
}
