package com.java.tutorial.project.domain.businessobject.area;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 * 行政区划表
 *
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@AutoMapper(target = AreaPO.class, reverseConvertGenerate = false)
public final class AreaCreateBO {

    /**
     * 编码
     */
    @NotNull(message = "请输入编码")
    private Integer code;
    /**
     * 名称
     */
    @NotBlank(message = "请输入名称")
    @Size(max = 10, message = "名称限制输入10个字符")
    private String name;
    /**
     * 父编码(0:省 其他:市县)
     */
    @NotNull(message = "请输入父编码(0:省 其他:市县)")
    private Integer parentCode;
    /**
     * 完整名称
     */
    @NotBlank(message = "请输入完整名称")
    @Size(max = 20, message = "完整名称限制输入20个字符")
    private String fullName;
    /**
     * 类型(1:省 2:市 3:县)
     */
    @NotNull(message = "请输入类型(1:省 2:市 3:县)")
    private Integer type;
    /**
     * 经度
     */
    @NotNull(message = "请输入经度")
    private Double lon;
    /**
     * 纬度
     */
    @NotNull(message = "请输入纬度")
    private Double lat;
    /**
     * 排序
     */
    @NotNull(message = "请输入排序")
    private Integer sort;
    /**
     * 天气网编码
     */
    @NotNull(message = "请输入天气网编码")
    private Long weatherCode;

}