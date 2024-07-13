package com.java.coco.BIExtend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class EntityD extends ParentEntity {

    private Boolean gender;
}
