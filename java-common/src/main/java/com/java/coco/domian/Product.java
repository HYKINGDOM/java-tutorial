package com.java.coco.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {


    private Long productId;

    private String productName;

    private String productDesc;

    private String productImg;

}
