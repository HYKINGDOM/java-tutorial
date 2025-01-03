package com.java.tutorial.project.core.nodeDefinition;


import com.java.tutorial.project.core.enums.NodeEnum;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class IfNode extends Node {

    private Node trueNode;

    private Node falseNode;

    public IfNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.IF);
    }

    public void setTrueNode(Node trueNode) {
        this.trueNode = trueNode;
        super.addNextNode(trueNode);
    }

    public void setFalseNode(Node falseNode) {
        this.falseNode = falseNode;
        super.addNextNode(falseNode);
    }
}