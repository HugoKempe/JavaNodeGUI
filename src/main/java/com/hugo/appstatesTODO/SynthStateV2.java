package com.hugo.appstatesTODO;

import com.hugo.main.App;
import com.hugo.synth.SynthManager;
import com.hugo.synthV2.SynthManagerV2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SynthStateV2 extends State implements StateMethods {

    private SynthManagerV2 synthManagerV2;

    public SynthStateV2(App app) {
        super(app);
        initClasses();
    }

    private void initClasses() {
        synthManagerV2 = new SynthManagerV2(this);
    }

    @Override
    public void update() {
        synthManagerV2.update();
    }

    @Override
    public void draw(Graphics g) {
        synthManagerV2.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        synthManagerV2.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        synthManagerV2.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        synthManagerV2.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        synthManagerV2.mouseDragged(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
