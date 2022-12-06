package com.hugo;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.data.Function;
import com.jsyn.unitgen.FunctionOscillator;
import com.jsyn.unitgen.LineOut;

public class PlayFunction {
    private void test() {
        // Create a context for the synthesizer.
        Synthesizer synth = JSyn.createSynthesizer();

        // Start synthesizer using default stereo output at 44100 Hz.
        synth.start();

        // Add a FunctionOscillator
        FunctionOscillator oscillator = new FunctionOscillator();
        synth.add(oscillator);

        // Define a function that gives the shape of the waveform.
        Function func = input -> {
            // Input ranges from -1.0 to 1.0
            double s = Math.sin(input * Math.PI * 2.0);
            return s * s * s * s;
        };

        oscillator.function.set(func);

        // Add a stereo audio output unit.
        LineOut lineOut = new LineOut();
        synth.add(lineOut);

        // Connect the oscillator to both channels of the output.
        oscillator.output.connect(0, lineOut.input, 0);
        oscillator.output.connect(0, lineOut.input, 1);

        // Set the frequency and amplitude for the sine wave.
        oscillator.frequency.set(345.0);
        oscillator.amplitude.set(0.6);

        // We only need to start the LineOut. It will pull data from the
        // oscillator.
        lineOut.start();

        System.out.println("You should now hear a sine wave for a few seconds.");

        // Sleep while the sound is generated in the background.
        try {
            double time = synth.getCurrentTime();
            // Sleep for a few seconds.
            synth.sleepUntil(time + 4.0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stop playing.");
        // Stop everything.
        synth.stop();
    }

    public static void main(String[] args) {
        new PlayFunction().test();
    }
}
