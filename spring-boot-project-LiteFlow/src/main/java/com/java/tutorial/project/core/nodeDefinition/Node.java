package com.java.tutorial.project.core.nodeDefinition;

import com.google.common.collect.Lists;

import com.java.tutorial.project.core.enums.NodeEnum;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public abstract class Node {

    // node的唯一id
    private final String id;

    // node名称，对应LIteFlow的Bean名称
    private final String name;

    // 入度
    private final List<Node> pre = Lists.newArrayList();

    // 节点类型
    private final NodeEnum nodeEnum;

    // 出度
    private final List<Node> next = Lists.newArrayList();

    protected Node(String id, String name, NodeEnum nodeEnum) {
        this.id = id;
        this.name = name;
        this.nodeEnum = nodeEnum;
    }

    public void addNextNode(Node node) {
        next.add(node);
    }

    public void addPreNode(Node preNode) {
        pre.add(preNode);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node)o;
        return Objects.equals(id, node.id) && Objects.equals(name, node.name) && Objects.equals(pre,
            node.pre) && nodeEnum == node.nodeEnum && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pre, nodeEnum, next);
    }
}