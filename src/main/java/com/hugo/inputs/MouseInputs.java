package com.hugo.inputs;

import com.hugo.main.AppPanel;
import com.hugo.synth.SynthPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInputs implements MouseListener, MouseMotionListener, MouseWheelListener {

    private AppPanel appPanel = null;
    private SynthPanel synthPanel = null;
    public MouseInputs(AppPanel appPanel) {
        this.appPanel = appPanel;
    }
    public MouseInputs(SynthPanel synthPanel) {
        this.synthPanel = synthPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (synthPanel != null) {
            synthPanel.getApp().getSynthState().mousePressed(e);
        } else {
            appPanel.getApp().getSynthState().mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (synthPanel != null) {
            synthPanel.getApp().getSynthState().mouseReleased(e);
        } else {
            appPanel.getApp().getSynthState().mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (synthPanel != null) {
            synthPanel.getApp().getSynthState().mouseDragged(e);
        } else {
            appPanel.getApp().getSynthState().mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (synthPanel != null) {
            synthPanel.getApp().getSynthState().mouseMoved(e);
        } else {
            appPanel.getApp().getSynthState().mouseMoved(e);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
