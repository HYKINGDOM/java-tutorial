package com.java.tutorial.project.core.factory;

import com.java.tutorial.project.core.enums.NodeEnum;
import com.java.tutorial.project.core.function.NodeFunction;
import com.java.tutorial.project.core.nodeDefinition.CommonNode;
import com.java.tutorial.project.core.nodeDefinition.EndNode;
import com.java.tutorial.project.core.nodeDefinition.IfNode;
import com.java.tutorial.project.core.nodeDefinition.Node;
import com.java.tutorial.project.core.nodeDefinition.StartNode;
import com.java.tutorial.project.core.nodeDefinition.SummaryNode;
import com.java.tutorial.project.core.nodeDefinition.SwitchNode;
import com.java.tutorial.project.core.nodeDefinition.WhenNode;


public class NodeFactory {

    public static Node getNode(NodeEnum nodeEnum, String id, String name) {
        return NodeFunction.createNode(nodeEnum,
                () -> new CommonNode(id, name),
                () -> new WhenNode(id, name),
                () -> new IfNode(id, name),
                () -> new SwitchNode(id, name),
                () -> new SummaryNode(id, name),
                () -> new StartNode(id, name),
                () -> new EndNode(id, name));
    }
}
