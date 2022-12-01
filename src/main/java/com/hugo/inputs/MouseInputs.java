package com.hugo.inputs;

import com.hugo.main.AppPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInputs implements MouseListener, MouseMotionListener, MouseWheelListener {

    private AppPanel appPanel;
    public MouseInputs(AppPanel appPanel) {
        this.appPanel = appPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        appPanel.getApp().getTesting().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        appPanel.getApp().getTesting().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        appPanel.getApp().getTesting().mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        appPanel.getApp().getTesting().mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
