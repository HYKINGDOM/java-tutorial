package com.java.tutorial.project.constant;

/**
 * @author meta
 */
public enum kafkaStreamPropertiesEnum {

    /**
     * ks1In
     */
    FIRST("ks1In", "", 2),

    /**
     * ks1Out
     */
    SECOND("ks1Out", "", 3);

    private String topic;

    private String groupId;

    private int partition;

    kafkaStreamPropertiesEnum(String topic, String groupId, int partition) {
        this.topic = topic;
        this.groupId = groupId;
        this.partition = partition;
    }
}
