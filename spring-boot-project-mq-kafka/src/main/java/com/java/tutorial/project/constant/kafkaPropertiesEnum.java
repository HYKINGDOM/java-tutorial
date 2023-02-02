package com.java.tutorial.project.constant;

/**
 * @author HY
 */
public enum kafkaPropertiesEnum {


    /**
     *
     */
    FIRST("event-topic-first","", 1),
    SECOND("event-topic-second","", 1);


    private String topic;


    private String groupId;


    private int partition;


    kafkaPropertiesEnum(String topic, String groupId, int partition) {
        this.topic = topic;
        this.groupId = groupId;
        this.partition = partition;
    }
}
