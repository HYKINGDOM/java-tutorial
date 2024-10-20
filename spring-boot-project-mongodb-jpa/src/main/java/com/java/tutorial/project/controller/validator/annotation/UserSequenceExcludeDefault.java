package com.java.tutorial.project.controller.validator.annotation;

import javax.validation.GroupSequence;

/**
 * @author meta
 */
@GroupSequence({UserCreate.class, UserUpdate.class})
public interface UserSequenceExcludeDefault {
}
