package com.java.tutorial.project.core.nodeDefinition;

import com.java.tutorial.project.core.enums.NodeEnum;
import lombok.NonNull;

public class CommonNode extends Node {

    public CommonNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.COMMON);
    }
}
