package com.hugo.appstatesTODO;

import com.hugo.main.App;
import com.hugo.nodes.NodeManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Testing extends State implements StateMethods {

    private NodeManager nodeManager;
    private int ticks;
    private double tempVal;

    public Testing(App app) {
        super(app);
        initClasses();
    }

    private void initClasses() {
        nodeManager = new NodeManager(this);
        ticks = 0;
    }

    @Override
    public void update() {
        nodeManager.update();
        ticks++;
        if (ticks % 10 == 1) {
            tempVal += 0.1;
            nodeManager.getTestNode(0).setInputValue(0, tempVal % 100);
            ticks = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        nodeManager.draw(g);
        /*g.drawString("Node(0) - In:  " + nodeManager.getTestNode(0).getInputValue(0), 10, 10);
        g.drawString("Node(0) - Out: " + nodeManager.getTestNode(0).getOutputValue(0), 10, 30);
        g.drawString("Node(1) - In:  " + nodeManager.getTestNode(1).getInputValue(0), 10, 50);
        g.drawString("Node(1) - Out: " + nodeManager.getTestNode(1).getOutputValue(0), 10, 70);*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        nodeManager.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        nodeManager.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        nodeManager.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        nodeManager.mouseMoved(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
