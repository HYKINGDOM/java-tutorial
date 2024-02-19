package com.java.tutorial.project.domain.businessobject.passport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * APP用户修改密码
 */
@Getter
@Setter
public class ResetPasswordBO {

    /**
     * 手机号
     */
    @Pattern(regexp = "^1([3-9])\\d{9}", message = "请输入11位的手机号")
    private String phone;
    /**
     * 密码
     */
    @Pattern(regexp = "^([a-zA-Z0-9]){8,20}$", message = "密码限制输入8到20位的数字或字母")
    private String password;
    /**
     * 验证码
     */
    @NotBlank(message = "请输入验证码")
    private String code;

}
