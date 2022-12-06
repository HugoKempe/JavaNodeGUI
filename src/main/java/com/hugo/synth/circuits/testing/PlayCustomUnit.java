package com.hugo.synth.circuits.testing;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;

public class PlayCustomUnit {
    private Synthesizer synth;
    private UnitOscillator osc;
    private CustomCubeUnit cuber;
    private LineOut lineOut;

    private void test() {
        synth = JSyn.createSynthesizer();
        synth.add(osc = new SineOscillator());

        synth.add(cuber = new CustomCubeUnit());

        synth.add(lineOut = new LineOut());

        osc.output.connect(0, cuber.input, 0);

        cuber.output.connect(0, lineOut.input, 1);

        osc.output.connect(0, lineOut.input, 0);

        osc.frequency.set(240.0);

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

    public static void main(String[] args) {
        new PlayCustomUnit().test();
    }
}
