package com.java.tutorial.tool.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author meta
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop implements Serializable {

    private static final long serialVersionUID = -2;

    private Long shopId;

    private String shopName;

    private List<Product> products;
}
