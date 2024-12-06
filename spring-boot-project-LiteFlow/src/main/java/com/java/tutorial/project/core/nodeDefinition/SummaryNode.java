package com.java.tutorial.project.core.nodeDefinition;


import com.java.tutorial.project.core.enums.NodeEnum;
import lombok.NonNull;


public class SummaryNode extends Node {

    public SummaryNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.SUMMARY);
    }
}
