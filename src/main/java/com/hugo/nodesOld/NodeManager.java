package com.hugo.nodesOld;

import com.hugo.appstatesTODO.Testing;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static com.hugo.main.App.APP_HEIGHT;
import static com.hugo.main.App.APP_WIDTH;

public class NodeManager {

    private Testing testing;
    private TestNode[] testNodes;
    private NodeInput selectedInput = null;
    private NodeOutput selectedOutput = null;
    private HashMap<NodeOutput, NodeInput> nodeBindings;
    private int mouseX, mouseY;
    private boolean outputIsSelected;
    private boolean inputIsSelected;

    public final static int NODE_IO_SIZE = 20;

    public NodeManager(Testing testing) {
        this.testing = testing;
        loadNodes();
    }

    private void loadNodes() {
        testNodes = new TestNode[2];
        testNodes[0] = new TestNode(200, APP_HEIGHT / 2 - 40, 100, 80);
        testNodes[1] = new TestNode(APP_WIDTH - 300, APP_HEIGHT / 2 - 40, 100, 80);
        nodeBindings = new HashMap<>();
    }

    public void update() {
        for (TestNode tn : testNodes) {
            tn.update();
        }
        updateBindings();
    }

    public void draw(Graphics g) {
        for (TestNode tn : testNodes) {
            tn.draw(g);
        }

        g.setColor(Color.BLACK);
        for (Map.Entry<NodeOutput, NodeInput> entry : nodeBindings.entrySet()) {
            g.drawLine(entry.getValue().getX(), entry.getValue().getY(), entry.getKey().getX(), entry.getKey().getY());
        }

        if (inputIsSelected) {
            g.drawLine(selectedInput.getX(), selectedInput.getY(), mouseX, mouseY);
        }
        if (outputIsSelected) {
            g.drawLine(selectedOutput.getX(), selectedOutput.getY(), mouseX, mouseY);
        }

        g.drawString("Selected Input: " + inputIsSelected,10, 100);
        g.drawString("Selected Output: " + outputIsSelected,10, 120);
    }

    public void mousePressed(MouseEvent e) {
        for (TestNode tn : testNodes) {
            for (NodeInput nodeInput : tn.getNodeInputs()) {
                if (isIN(e, nodeInput)) {
                    nodeInput.setMousePressed(true);
                }
            }
            for (NodeOutput nodeOutput : tn.getNodeOutputs()) {
                if (isIN(e, nodeOutput)) {
                    nodeOutput.setMousePressed(true);
                    selectedOutput = nodeOutput;
                    outputIsSelected = true;
                    System.out.println("Selected output: " + selectedOutput);
                }
            }

            if (isIn(e, tn))
                tn.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {

        for (TestNode tn : testNodes) {
            for (NodeInput nodeInput : tn.getNodeInputs()) {
                if (isIN(e, nodeInput)) {
                    nodeInput.setMousePressed(false);
                    selectedInput = nodeInput;
                    System.out.println("Released Input: " + selectedInput);
                    System.out.println("Created a connection between (" + selectedOutput + " : " + selectedInput + ")");
                    bindNodes(selectedOutput, selectedInput);
                } else if (outputIsSelected) {
                    removeBind(selectedOutput);
                }
                nodeInput.resetBools();
            }

            for (NodeOutput nodeOutput : tn.getNodeOutputs()) {
                if (isIN(e, nodeOutput)) {
                    nodeOutput.setMousePressed(false);
                }
                nodeOutput.resetBools();
            }

            if (isIn(e, tn))
                tn.setMousePressed(false);

            tn.resetBools();
            inputIsSelected = false;
            outputIsSelected = false;
        }
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        for (TestNode tn : testNodes) {
            tn.setMouseOver(false);
            for (NodeInput nodeInput : tn.getNodeInputs()) {
                nodeInput.setMouseOver(false);
                if (isIN(e, nodeInput))
                    nodeInput.setMouseOver(true);
            }

            for (NodeOutput nodeOutput : tn.getNodeOutputs()) {
                nodeOutput.setMouseOver(false);
                if (isIN(e, nodeOutput))
                    nodeOutput.setMouseOver(true);
            }


            if (isIn(e, tn))
                tn.setMouseOver(true);
        }
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void bindNodes(NodeOutput from, NodeInput to) {
        //to.setValue(from.getValue());
        nodeBindings.put(from, to);
        to.hasConnection = true;
        selectedInput = null;
        selectedOutput = null;
    }

    private void removeBind(NodeOutput from) {
        nodeBindings.remove(from);
    }
    public void updateBindings() {
        for (Map.Entry<NodeOutput, NodeInput> entry : nodeBindings.entrySet()) {
            entry.getValue().setValue(entry.getKey().getValue());
        }
    }

    private boolean isIn(MouseEvent e, BaseNode bn) {
        return bn.getBounds().contains(e.getX(), e.getY());
    }

    private boolean isIN(MouseEvent e, BaseNodeIO bn) {
        return bn.getBounds().contains(e.getX(), e.getY());
    }

    public TestNode getTestNode(int index) {
        return testNodes[index];
    }
}
