package com.hugo.synth;

import com.hugo.synth.circuits.WindCircuit;
import com.hugo.utilz.Constants;
import com.hugo.utilz.HelpMethods;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.instruments.DualOscillatorSynthVoice;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.LinearRamp;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.unitgen.UnitSource;

import java.awt.*;

import static com.hugo.main.App.SYNTH_HEIGHT;
import static com.hugo.main.App.SYNTH_WIDTH;

import static com.hugo.synth.SynthManager.TILES;
import static com.hugo.synth.SynthManager.TILES_SIZE;

public class Synth extends BaseSynth {

    private boolean mouseOver, mousePressed;
    private boolean muted;
    private Color color;

    private Synthesizer synthesizer;
    private UnitOscillator osc;
    private LinearRamp lag;
    private LineOut lineOut;
    private UnitSource unitSource;
    private WindCircuit windCircuit;

    private SynthEngine synthEngine;
    private MidiManager manager;

    public Synth(int x, int y, int width, int height) {
        super(x, y, width, height);
        //initSynth();
        testCircuit();
        synthEngine = new SynthEngine(128);
        //manager = new MidiManager();
        //start();
    }



    private void initSynth() {
        synthesizer = JSyn.createSynthesizer();

        synthesizer.add(osc = new SawtoothOscillatorBL());
        synthesizer.add(lag = new LinearRamp());
        synthesizer.add(lineOut = new LineOut());
        osc.output.connect(0, lineOut.input, 0);
        osc.output.connect(0, lineOut.input, 1);

        lag.output.connect(osc.amplitude);
        lag.input.setup(0.0, 0.5, 1.0);
        lag.time.set(0.2);

        osc.frequency.setup(50.0, 300.0, 10000.0);
    }

    private void testCircuit() {
        synthesizer = JSyn.createSynthesizer();
        synthesizer.add(lineOut = new LineOut());
        windCircuit = new WindCircuit();
        unitSource = windCircuit;
        synthesizer.add(unitSource.getUnitGenerator());

        unitSource.getOutput().connect(0, lineOut.input, 0);
        unitSource.getOutput().connect(0, lineOut.input, 1);

        //unitSource.getOutput().connect(0, lineOut.input, 0);
        //unitSource.getOutput().connect(0, lineOut.input, 1);

    }

    private UnitSource createUnitSource() {
        //return new SampleHoldNoteBlaster();
        //return new com.syntona.exported.FMVoice();
        //return new DualOscillatorSynthVoice();
        return new WindCircuit();
        //return new WhiteNoise();
        //return new BrownNoise();
    }

    private void start() {
        synthesizer.start();
        //wind.start();
        lineOut.start();
    }

    public void setFreq(double value) {
        System.out.println(value);
        //windCircuit.passThrough.input.set(HelpMethods.map(value, 0.0, 100.0, 40.0, 6000.0));
        //System.out.println(windCircuit.passThrough.input.getMaximum() + " | " + windCircuit.passThrough.input.getMinimum() + " | " + windCircuit.passThrough.input.getValue());
        synthEngine.setAttack(value);
    }

    public void update() {
        color = Color.magenta;
        /*osc.frequency.set(440.0);
        windCircuit.noiseAmp.set(1.0);
        windCircuit.noiseAmp.set(0.5);
        if (mouseOver) {
            color = Color.BLUE;
            windCircuit.noiseAmp.set(0.5);
        }
        if (mousePressed) {
            color = Color.green;
            osc.frequency.set(440.0 * 4);
            windCircuit.noiseAmp.set(0.1);
        }*/
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Constants.UI.Colors.BACKGROUND_COLOR);
        g2.fillRect(x, y, width, height);

        g2.setColor(Constants.UI.Colors.LINE_COLOR_2);
        g2.setStroke(new BasicStroke(2));

        int smallTiles = TILES / 4;
        int smallTilesSize = TILES_SIZE / smallTiles;

        for (int row = 0; row < (TILES * smallTiles) - TILES; row++) {
            g2.drawLine(x, y + row * smallTilesSize, x + SYNTH_WIDTH, y + row * smallTilesSize);
        }
        for (int col = 0; col < TILES * smallTiles; col++) {
            g2.drawLine(x + (col * smallTilesSize), y, (x + col * smallTilesSize), y + SYNTH_HEIGHT);
        }

        g2.setColor(Constants.UI.Colors.LINE_COLOR_1);
        g2.setStroke(new BasicStroke(3));
        /*g2.drawLine(x, y, x + SYNTH_WIDTH, y);
        g2.drawLine(x, y, x, y + SYNTH_HEIGHT);*/

        for (int row = 1; row < TILES - 4; row++) {
            g2.drawLine(x, y + row * TILES_SIZE, x + SYNTH_WIDTH, y + row * TILES_SIZE);
        }
        for (int col = 1; col < TILES; col++) {
            g2.drawLine(x + (col * TILES_SIZE), y, (x + col * TILES_SIZE), y + SYNTH_HEIGHT);
        }

        g2.setColor(Constants.UI.Colors.MAIN_COLOR);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x, y, width, height);
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

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public SynthEngine getSynthEngine() {
        return synthEngine;
    }
}
