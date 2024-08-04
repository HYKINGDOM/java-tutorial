package com.java.coco.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author meta
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TalentInfo {


    private Long id;


    private String name;

    private List<Product> products;

}
