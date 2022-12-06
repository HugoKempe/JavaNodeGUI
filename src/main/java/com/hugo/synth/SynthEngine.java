package com.hugo.synth;

import com.hugo.utilz.LoadSave;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.data.FloatSample;
import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.unitgen.Add;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.unitgen.VariableRateDataReader;
import com.jsyn.unitgen.VariableRateMonoReader;
import com.jsyn.unitgen.VariableRateStereoReader;
import com.jsyn.util.MultiChannelSynthesizer;
import com.jsyn.util.SampleLoader;
import com.jsyn.util.VoiceDescription;
import com.softsynth.shared.time.TimeStamp;

import java.io.File;
import java.util.ArrayList;

public class SynthEngine implements Runnable {

    private Synthesizer synth;
    private UnitOscillator osc;
    private LineOut lineOut;
    private SegmentedEnvelope envelope;
    private VariableRateDataReader envelopePlayer;
    private VariableRateDataReader samplePlayer;
    private FloatSample sample;

    private Thread thread;

    private File kickSampleFile;

    private MultiChannelSynthesizer multiSynth;
    private MidiSynthesizer midiSynthesizer;
    private static final int NUM_CHANNELS = 16;
    private static final int VOICES_PER_CHANNEL = 6;

    private int bpm;
    double[] pairs = {
            0.1, 1.0, 0.2, 0.3, 0.6, 0.0
    };

    public SynthEngine(int bpm) {
        this.bpm = bpm;
        // Create a context for the synthesizer.
        synth = JSyn.createSynthesizer();
        // Add a tone generator.
        synth.add(osc = new SineOscillator());
        // Add an evelope player.
        synth.add(envelopePlayer = new VariableRateMonoReader());

        // Create an envelope consisting of (duration,value) pairs.
        envelope = new SegmentedEnvelope(pairs);

        SampleLoader.setJavaSoundPreferred(false);
        //sample = SampleLoader.loadFloatSample(new File("/Users/hugo.kempe/Documents/NodeGUI/src/test/resources/Samples/kick.wav"));
        sample = LoadSave.loadSample("/Users/hugo.kempe/Documents/NodeGUI/src/test/resources/Samples/kick.wav");
        if (sample.getChannelsPerFrame() == 1) {
            synth.add(samplePlayer = new VariableRateMonoReader());
            samplePlayer.output.connect(0, lineOut.input, 0);
        } else if (sample.getChannelsPerFrame() == 2) {
            synth.add(samplePlayer = new VariableRateStereoReader());
           // samplePlayer.output.connect(0, lineOut.input, 0);
            //samplePlayer.output.connect(1, lineOut.input, 1);
        } else {
            throw new RuntimeException("Can only play mono or stereo samples.");
        }


        // Add an output mixer.
        synth.add(lineOut = new LineOut());
        envelopePlayer.output.connect(osc.amplitude);
        // Connect the oscillator to the output.
        osc.output.connect(0, lineOut.input, 0);
        osc.output.connect(0, lineOut.input, 1);

        // Start synthesizer using default stereo output at 44100 Hz.
        synth.start();

        samplePlayer.rate.set(sample.getFrameRate());

        // We only need to start the LineOut. It will pull data from the other
        // units.
        lineOut.start();

        startSynth();

    }

    private void setupSynth(VoiceDescription description) {
        synth = JSyn.createSynthesizer();
        synth.add(lineOut = new LineOut());

    }

    public void setAttack(double val) {
        pairs[1] = val;
        envelope.write(pairs);
    }

    public void setDecay(double val) {
        pairs[3] = val;
        envelope.write(pairs);
    }

    public void setHold(double val) {
        pairs[4] = val;
        envelope.write(pairs);
    }

    public void setSustain(double val) {
        /*pairs[3] = val;
        envelope.write(pairs);*/
    }
    public void setRelease(double val) {
        /*pairs[5] = val;
        envelope.write(pairs);*/
    }

    @Override
    public void run() {
        try {
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

            do {
                synth.sleepFor(1.0);
            } while (samplePlayer.dataQueue.hasMore());

            synth.sleepFor(0.5);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synth.stop();

        // Wait until the sample has finished playing.
        ;
/*        int index = 0;
        while (true) {
            envelope.setSustainBegin(2);
            envelope.setSustainEnd(2);
            envelopePlayer.dataQueue.clear();
            envelopePlayer.dataQueue.queueOn(envelope);
            System.out.println(envelope.getNumFrames());
            envelopePlayer.start();
            if (index % 3 == 0) {
                osc.frequency.set(440.0 / 2);
            } else if (index % 3 == 1) {
                osc.frequency.set(440.0);
            } else if (index % 3 == 2) {
                osc.frequency.set(440.0 * 2);
            }*//* else if (index % 4 == 3) {
                osc.frequency.set(440.0 / 2);
            }*//*
            index++;
            try {
                synth.sleepFor(60.0 / bpm);
                envelopePlayer.dataQueue.queueOff(envelope); // release
                synth.sleepFor(60.0 / bpm);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
    }

    public void setBpm(int bpm) {

        this.bpm = bpm;
        System.out.println(bpm);
    }

    public void startSynth() {
        thread = new Thread(this);
        thread.start();
    }

    public UnitOscillator getOsc() {
        return osc;
    }

    public LineOut getLineOut() {
        return lineOut;
    }
}
