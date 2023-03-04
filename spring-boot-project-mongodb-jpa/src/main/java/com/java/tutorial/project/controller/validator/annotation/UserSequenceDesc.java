package com.java.tutorial.project.controller.validator.annotation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author hy
 */
@GroupSequence({UserUpdate.class, UserCreate.class, Default.class})
public interface UserSequenceDesc {
}
