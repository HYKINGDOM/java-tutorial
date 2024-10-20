package com.java.data.controller.validator;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author meta
 */
@GroupSequence({UserCreate.class, UserUpdate.class, Default.class})
public interface UserSequence {
}
