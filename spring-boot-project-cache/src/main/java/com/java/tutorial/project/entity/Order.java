package com.java.tutorial.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HY
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    Long id;

    String orderNumber;

    Double money;

    Integer status;

}
