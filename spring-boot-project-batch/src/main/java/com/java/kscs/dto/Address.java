package com.java.kscs.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * 用户类
 *
 * @author hy
 */
@AllArgsConstructor
@Getter
@ToString
@FieldNameConstants
public class Address {
    private String addressId;
    private String address;
    private String address2;
    private String district;
    private String cityId;
    private String postalCode;
    private String phone;
    private String lastUpdate;
}
