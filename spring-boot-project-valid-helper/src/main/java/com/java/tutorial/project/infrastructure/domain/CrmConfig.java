package com.java.tutorial.project.infrastructure.domain;

import com.github.houbb.validator.core.annotation.constraint.Ranges;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author meta
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrmConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键Id */
    private Long id;

    @NotBlank(message = "业务类型不能为空")
    private String urgeType;

    @NotBlank(message = "方式不能为空")
    private String urgeMode;

    /** 账号 */
    @NotBlank(message = "账号不能为空")
    private String urgeAccount;

    /** 关联品牌 */
    @Ranges({"boy", "girl"})
    private String relevanceBrand;

    /** 文案内容 */
    @NotBlank(message = "文案内容不能为空")
    @Length(max = 5000, message = "文案内容过长")
    private String urgeContent;

}

