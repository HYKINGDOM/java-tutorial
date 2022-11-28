package com.java.kscs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;


/**
 *
 * @author HY
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldNameConstants
public class Address1 {

    private String addressId;
    private String address;
    private String address2;
    private String district;
    private String cityId;
    private String postalCode;
    private String phone;
    private String lastUpdate;
}
