package com.java.tutorial.project.util;

import lombok.Getter;

@Getter
public enum ErrorCode {
    /**
     * 公共
     */
    SUCCESS("0000", "THE_OPERATION_SUCCEEDED"), FAIL("0001", "fail"), ERROR("0002", "abnormal"),
    PARAM_NULL_ERROR("0003", "the_parameter_is_empty"), PARAM_SKU_NULL_ERROR("0004", "THE_SKU_PARAMETER_IS_EMPTY"),
    PARAM_PRICE_NULL_ERROR("0005", "the_price_parameter_is_empty"),
    PARAM_STOCK_NULL_ERROR("0006", "the_inventory_parameter_is_empty"),
    PARAM_PRICE_ILLEGAL_ERROR("0007", "illegal_price_parameters"),
    PARAM_STOCK_ILLEGAL_ERROR("0008", "ILLEGAL_INVENTORY_PARAMETERS"),
    ;

    private String code;

    private String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
