package com.java.data.controller.validator;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author HY
 */
@GroupSequence({UserCreate.class, UserUpdate.class, Default.class})
public interface UserSequence {
}
