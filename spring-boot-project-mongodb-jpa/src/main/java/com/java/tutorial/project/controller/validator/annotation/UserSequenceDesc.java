package com.java.tutorial.project.controller.validator.annotation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author meta
 */
@GroupSequence({UserUpdate.class, UserCreate.class, Default.class})
public interface UserSequenceDesc {
}
