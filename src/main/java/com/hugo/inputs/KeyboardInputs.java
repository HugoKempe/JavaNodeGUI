package com.hugo.inputs;

import com.hugo.main.AppPanel;
import com.hugo.synth.SynthPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private AppPanel appPanel;
    private SynthPanel synthPanel;
    public KeyboardInputs(AppPanel appPanel) {
        this.appPanel = appPanel;
    }

    public KeyboardInputs(SynthPanel synthPanel) {
        this.synthPanel = synthPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed - (code: " + e.getKeyCode() + " | char: " + e.getKeyChar() + ")");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released - (code: " + e.getKeyCode() + " | char: " + e.getKeyChar() + ")");
    }
}
