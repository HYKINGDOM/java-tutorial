package com.java.tutorial.project.core.enums;

/**
 * @author meta
 */
public enum NodeEnum {
    // 普通节点
    COMMON,
    // 并行节点
    WHEN,
    // 判断节点
    IF,
    // 选择节点
    SWITCH,
    // 汇总节点（自定义）
    SUMMARY,
    // 开始节点（自定义）
    START,
    // 结束节点（自定义）
    END;

    public static NodeEnum valueByName(String nodeType) {
        NodeEnum[] values = values();
        for (NodeEnum value : values) {
            if (value.name().equals(nodeType)) {
                return value;
            }
        }
        return null;
    }
}
