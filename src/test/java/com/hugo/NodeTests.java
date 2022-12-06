package com.hugo;

import com.hugo.nodesOld.MathNode;
import com.hugo.nodesOld.ValueNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class NodeTests {

    @Test
    public void testMathNode() {
        MathNode mathNode = new MathNode();

        mathNode.setType(MathNode.ADD);
        mathNode.setInput1(1.5);
        mathNode.setInput2(1.8);
        mathNode.update();

        double expectedResult = 3.3;
        double actualResult = mathNode.getOutput();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testValueNode() {
        MathNode mathNode = new MathNode();
        ArrayList<ValueNode> valueNodes = new ArrayList<>();
        valueNodes.add(new ValueNode(5));
        valueNodes.add(new ValueNode(3));

        mathNode.setType(MathNode.SUBTRACT);

        mathNode.setInput1(valueNodes.get(0).getValue());
        mathNode.setInput2(valueNodes.get(1).getValue());

        double expectedResult = 2.0;
        double actualResult = mathNode.getOutput();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
