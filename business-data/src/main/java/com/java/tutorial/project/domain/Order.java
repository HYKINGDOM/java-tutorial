package com.java.tutorial.project.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author HY
 */
@Data
@Builder
public class Order {

    Long id;

    String orderNumber;

    Double money;

    Integer status;
}
