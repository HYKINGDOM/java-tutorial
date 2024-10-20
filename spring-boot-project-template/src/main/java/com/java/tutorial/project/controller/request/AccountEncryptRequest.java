package com.java.tutorial.project.controller.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author meta
 */
@Builder
@Data
public class AccountEncryptRequest {

    @NotEmpty
    @Size(min = 10, max = 18)
    private Long accountId;

    @NotEmpty
    @Size(min = 10, max = 18)
    private String accountName;

    @NotEmpty
    @Size(min = 6, max = 18)
    private String nickName;

    @NotEmpty

    private String password;

    private String icon;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 10, max = 18)
    private Integer cellphone;

    @Size(max = 40)
    private String note;

}
