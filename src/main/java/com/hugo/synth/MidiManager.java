package com.hugo.synth;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.instruments.DualOscillatorSynthVoice;
import com.jsyn.midi.MidiConstants;
import com.jsyn.midi.MidiSynthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.util.MultiChannelSynthesizer;
import com.jsyn.util.VoiceDescription;

import java.io.IOException;

public class MidiManager {
    private static final int NUM_CHANNELS = 16;
    private static final int VOICES_PER_CHANNEL = 6;
    private Synthesizer synth;
    private MidiSynthesizer midiSynthesizer;
    private LineOut lineOut;

    private VoiceDescription voiceDescription;
    private MultiChannelSynthesizer multiSynth;

    public MidiManager() {
        try {
            VoiceDescription description = DualOscillatorSynthVoice.getVoiceDescription();
            run(description);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMidiMessage(byte[] bytes) {
        midiSynthesizer.onReceive(bytes, 0, bytes.length);
    }

    public void sendNoteOff(int channel, int pitch, int velocity) {
        midiCommand(MidiConstants.NOTE_OFF + channel, pitch, velocity);
    }

    public void sendNoteOn(int channel, int pitch, int velocity) {
        midiCommand(MidiConstants.NOTE_ON + channel, pitch, velocity);
    }

    public int run(VoiceDescription description) throws IOException, InterruptedException {
        setupSynth(description);

        playNote();

        return 0;
    }

    private void sendProgramChange(int channel, int program) {
        midiCommand(MidiConstants.PROGRAM_CHANGE + channel, program);
    }

    private void midiCommand(int status, int data1, int data2) {
        byte[] buffer = new byte[3];
        buffer[0] = (byte) status;
        buffer[1] = (byte) data1;
        buffer[2] = (byte) data2;
        sendMidiMessage(buffer);
    }

    private void midiCommand(int status, int data1) {
        byte[] buffer = new byte[2];
        buffer[0] = (byte) status;
        buffer[1] = (byte) data1;
        sendMidiMessage(buffer);
    }

    private void playNote() throws InterruptedException {
        sendProgramChange(0, 0);
        sendProgramChange(1, 0);

        System.out.println("These two notes should play at the same pitch.");
        sendNoteOn(0, 61, 100);
        synth.sleepFor(0.5);
        sendNoteOff(0, 61, 100);
    }

    private void setupSynth(VoiceDescription description) {
        synth = JSyn.createSynthesizer();
        synth.add(lineOut = new LineOut());

        voiceDescription = description;
        multiSynth = new MultiChannelSynthesizer();
        final int startChanel = 0;
        multiSynth.setup(synth, startChanel, NUM_CHANNELS, VOICES_PER_CHANNEL, voiceDescription);
        midiSynthesizer = new MidiSynthesizer(multiSynth);

        multiSynth.getOutput().connect(0,lineOut.input,0);
        multiSynth.getOutput().connect(1,lineOut.input,1);

        synth.start();
        lineOut.start();
    }
}
