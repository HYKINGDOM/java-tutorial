package com.java.tutorial.project.domain.businessobject.passport;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * APP用户更换手机号
 *
 *
 */
@Getter
@Setter
public class PhoneCheckBO {

    /**
     * 手机号
     */
    @Pattern(regexp = "^1([3-9])\\d{9}", message = "请输入11位的手机号")
    private String phone;

}
