package com.java.tutorial.tool.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Order implements Serializable {

    private static final long serialVersionUID = -3;

    private Long orderId;
    private List<Shop> shops;
}
