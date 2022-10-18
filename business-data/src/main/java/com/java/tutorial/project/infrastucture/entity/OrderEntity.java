package com.java.tutorial.project.infrastucture.entity;


import lombok.Builder;
import lombok.Data;

/**
 * @author HY
 */
@Data
@Builder
@Ta
public class OrderEntity {

    Long id;

    String orderNumber;

    Double money;

    Integer status;
}
