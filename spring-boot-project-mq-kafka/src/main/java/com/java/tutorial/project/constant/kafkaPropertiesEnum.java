package com.java.tutorial.project.constant;

/**
 * @author meta
 */
public enum kafkaPropertiesEnum {

    /**
     * event-topic-first
     */
    FIRST("event-topic-first", "", 1),

    /**
     * event-topic-second
     */
    SECOND("event-topic-second", "", 1);

    private String topic;

    private String groupId;

    private int partition;

    kafkaPropertiesEnum(String topic, String groupId, int partition) {
        this.topic = topic;
        this.groupId = groupId;
        this.partition = partition;
    }
}
