package com.java.tutorial.project.controller.request;


import com.java.tutorial.project.controller.validator.annotation.PassWordValid;
import com.java.tutorial.project.controller.validator.annotation.UserValid;
import com.java.tutorial.project.controller.validator.Severity;
import com.java.tutorial.project.controller.validator.annotation.UserCreate;
import com.java.tutorial.project.controller.validator.annotation.UserUpdate;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import static com.java.tutorial.project.config.RequestInvalidConstant.FIELD_IS_BLANK_FAILED;
import static com.java.tutorial.project.config.RequestInvalidConstant.FIELD_IS_EMAIL_FAILED;
import static com.java.tutorial.project.config.RequestInvalidConstant.FIELD_IS_EMPTY_FAILED;
import static com.java.tutorial.project.config.RequestInvalidConstant.FIELD_IS_LENGTH_FAILED;
import static com.java.tutorial.project.config.RequestInvalidConstant.FIELD_IS_MAX_FAILED;
import static com.java.tutorial.project.config.RequestInvalidConstant.FIELD_IS_MIN_FAILED;
import static com.java.tutorial.project.config.RequestInvalidConstant.FIELD_IS_REGULAR_FAILED;


/**
 * @author HY
 */
@UserValid
@Builder
@Data
public class UserRequest {

    @Length(min = 8, max = 20, message = FIELD_IS_LENGTH_FAILED, groups = UserCreate.class)
    @NotBlank(message = FIELD_IS_BLANK_FAILED, groups = UserUpdate.class)
    private String userName;

    @NotEmpty(message = FIELD_IS_EMPTY_FAILED, groups = UserUpdate.class, payload = Severity.Info.class)
    private String userAccountNum;


    @Length(min = 8, max = 20, message = FIELD_IS_LENGTH_FAILED, groups = UserCreate.class)
    @NotEmpty(message = FIELD_IS_EMPTY_FAILED, payload = Severity.Warn.class)
    private String niceName;

    @Max(value = 99, message = FIELD_IS_MAX_FAILED, payload = Severity.Error.class)
    @Min(value = 10, message = FIELD_IS_MIN_FAILED, payload = Severity.Warn.class)
    private Integer age;

    @PassWordValid(pattern = "[\\w.]", message = FIELD_IS_REGULAR_FAILED, groups = UserCreate.class, payload = Severity.Error.class)
    @Length(min = 8, max = 20, message = FIELD_IS_LENGTH_FAILED)
    @NotBlank(message = FIELD_IS_BLANK_FAILED)
    private String passWord;

    @Email(message = FIELD_IS_EMAIL_FAILED)
    @NotEmpty(message = FIELD_IS_EMPTY_FAILED, groups = UserUpdate.class)
    private String userEmail;
}
