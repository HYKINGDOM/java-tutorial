package com.java.tutorial.project.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * 枚举值注解的校验类
 *
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

	private String[] strVal;
	private int[] intVal;

	@Override
	public void initialize(EnumValue constraintAnnotation) {
		strVal = constraintAnnotation.strVal();
		intVal = constraintAnnotation.intVal();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value instanceof String) {
			for (String str : strVal) {
				if (str.equals(value)) {
					return true;
				}
			}
		} else if (value instanceof Integer val) {
			for (int num : intVal) {
				if (num == val.intValue()) {
					return true;
				}
			}
		}

		return false;
	}

}