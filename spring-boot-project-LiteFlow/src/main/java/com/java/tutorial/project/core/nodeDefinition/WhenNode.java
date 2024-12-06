package com.java.tutorial.project.core.nodeDefinition;


import com.java.tutorial.project.core.enums.NodeEnum;
import lombok.NonNull;


public class WhenNode extends Node {

    public WhenNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.WHEN);
    }
}
