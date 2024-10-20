package com.java.tutorial.project.controller.validator.annotation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author meta
 */
@GroupSequence({UserCreate.class, UserUpdate.class, Default.class})
public interface UserSequence {
}
