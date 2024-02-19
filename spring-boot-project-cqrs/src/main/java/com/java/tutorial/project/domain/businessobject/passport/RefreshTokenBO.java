package com.java.tutorial.project.domain.businessobject.passport;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * APP用户刷新Token
 */
@Getter
@Setter
public class RefreshTokenBO {

    @NotBlank(message = "请输入刷新令牌")
    private String refreshToken;

}
