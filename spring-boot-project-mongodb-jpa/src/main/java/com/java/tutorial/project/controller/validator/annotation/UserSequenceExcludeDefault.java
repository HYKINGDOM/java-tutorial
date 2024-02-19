package com.java.tutorial.project.controller.validator.annotation;

import javax.validation.GroupSequence;

/**
 * @author hy
 */
@GroupSequence({UserCreate.class, UserUpdate.class})
public interface UserSequenceExcludeDefault {
}
