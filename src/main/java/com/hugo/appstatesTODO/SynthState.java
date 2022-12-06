package com.hugo.appstatesTODO;

import com.hugo.main.App;
import com.hugo.nodes.NodeManager;
import com.hugo.synth.SynthManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SynthState extends State implements StateMethods {

    private SynthManager synthManager;

    public SynthState(App app) {
        super(app);
        initClasses();
    }


    private void initClasses() {
        synthManager = new SynthManager(this);
    }

    @Override
    public void update() {
        synthManager.update();
    }

    @Override
    public void draw(Graphics g) {
        synthManager.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        synthManager.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        synthManager.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        synthManager.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        synthManager.mouseDragged(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
