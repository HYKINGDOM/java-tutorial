package com.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

/**
 * 用户类
 *
 * @author hy
 */
@Data
@Builder
@AllArgsConstructor
@FieldNameConstants
public class Address {
    private Long addressId;
    private String address;
    private String address2;
    private String district;
    private String cityId;
    private String postalCode;
    private String phone;
    private Date lastUpdate;
}
