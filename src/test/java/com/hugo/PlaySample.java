package com.hugo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.hugo.utilz.LoadSave;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.data.FloatSample;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.VariableRateDataReader;
import com.jsyn.unitgen.VariableRateMonoReader;
import com.jsyn.unitgen.VariableRateStereoReader;
import com.jsyn.util.SampleLoader;
import com.softsynth.shared.time.TimeStamp;

public class PlaySample {
    private Synthesizer synth;
    private VariableRateDataReader samplePlayer;
    private LineOut lineOut;

    private void test() {

        URL sampleFile;
        try {
            //sampleFile = new URL("/Users/hugo.kempe/Documents/NodeGUI/src/test/resources/Samples/kick.wav");
            sampleFile = new URL("http://www.softsynth.com/samples/NotHereNow22K.wav");
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return;
        }

        synth = JSyn.createSynthesizer();

        FloatSample sample;
        try {
            // Add an output mixer.
            synth.add(lineOut = new LineOut());

            // Load the sample and display its properties.
            SampleLoader.setJavaSoundPreferred(false);
            //sample = SampleLoader.loadFloatSample(new File("/Users/hugo.kempe/Documents/NodeGUI/src/test/resources/Samples/kick.wav"));
            sample = LoadSave.loadSample("/Users/hugo.kempe/Documents/NodeGUI/src/test/resources/Samples/kick.wav");
            System.out.println("Sample has: channels  = " + sample.getChannelsPerFrame());
            System.out.println("            frames    = " + sample.getNumFrames());
            System.out.println("            rate      = " + sample.getFrameRate());
            System.out.println("            loopStart = " + sample.getSustainBegin());
            System.out.println("            loopEnd   = " + sample.getSustainEnd());

            if (sample.getChannelsPerFrame() == 1) {
                synth.add(samplePlayer = new VariableRateMonoReader());
                samplePlayer.output.connect(0, lineOut.input, 0);
            } else if (sample.getChannelsPerFrame() == 2) {
                synth.add(samplePlayer = new VariableRateStereoReader());
                samplePlayer.output.connect(0, lineOut.input, 0);
                samplePlayer.output.connect(1, lineOut.input, 1);
            } else {
                throw new RuntimeException("Can only play mono or stereo samples.");
            }

            // Start synthesizer using default stereo output at 44100 Hz.
            synth.start();

            samplePlayer.rate.set(sample.getFrameRate());

            // We only need to start the LineOut. It will pull data from the
            // sample player.
            lineOut.start();

            // We can simply queue the entire file.
            // Or if it has a loop we can play the loop for a while.
            if (sample.getSustainBegin() < 0) {
                System.out.println("queue the sample");
                samplePlayer.dataQueue.queueLoop( sample, 0, 100*200 );
            } else {
                System.out.println("queueOn the sample for a short time");
                samplePlayer.dataQueue.queueOn(sample);
                synth.sleepFor(8.0);
                System.out.println("queueOff the sample");
                samplePlayer.dataQueue.queueOff(sample);
            }




            // Wait until the sample has finished playing.
            do {
                synth.sleepFor(1.0);
            } while (samplePlayer.dataQueue.hasMore());

            synth.sleepFor(0.5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Stop everything.
        synth.stop();
    }

    public static void main(String[] args) {
        new PlaySample().test();
    }
}
