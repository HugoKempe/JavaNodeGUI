package com.hugo.nodes;

import com.hugo.appstatesTODO.Testing;
import com.hugo.nodes.BaseNode;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static com.hugo.main.App.APP_HEIGHT;
import static com.hugo.main.App.APP_WIDTH;

public class NodeManager {

    private Testing testing;
    private TestNode testNode;

    public NodeManager(Testing testing) {
        this.testing = testing;
        loadNodes();
    }

    private void loadNodes() {
        testNode = new TestNode(200, APP_HEIGHT / 2 - 40, 100, 80);
    }

    public void update() {
        testNode.update();
    }

    public void draw(Graphics g) {
        testNode.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, testNode))
            testNode.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, testNode))
            testNode.setMousePressed(false);
    }

    public void mouseMoved(MouseEvent e) {
        testNode.setMouseOver(false);

        if (isIn(e, testNode)) {
            testNode.setMouseOver(true);
        }
    }

    public void mouseDragged(MouseEvent e) {

    }

    private boolean isIn(MouseEvent e, BaseNode bn) {
        return bn.getBounds().contains(e.getX(), e.getY());
    }
}
