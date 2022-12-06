package com.hugo.synth.circuits.testing;

import com.hugo.synth.circuits.WindCircuit;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitSource;

public class CircuitTester {
    private Synthesizer synth;
    private LineOut lineOut;
    private UnitSource unitSource;

    private void test() {
        synth = JSyn.createSynthesizer();
        synth.add(lineOut = new LineOut());

        unitSource = new WindCircuit();
        synth.add(unitSource.getUnitGenerator());

        unitSource.getOutput().connect(0, lineOut.input, 0);
        unitSource.getOutput().connect(0, lineOut.input, 1);


        synth.start();

        lineOut.start();

        try {
            double time = synth.getCurrentTime();

            synth.sleepUntil(time + 10.0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synth.stop();
    }

    private UnitSource createUnitSource() {
        //return new SampleHoldNoteBlaster();
        //return new com.syntona.exported.FMVoice();
        //return new DualOscillatorSynthVoice();
        return new WindCircuit();
        //return new WhiteNoise();
        //return new BrownNoise();
    }

    public static void main(String[] args) {
        new CircuitTester().test();
    }
}
