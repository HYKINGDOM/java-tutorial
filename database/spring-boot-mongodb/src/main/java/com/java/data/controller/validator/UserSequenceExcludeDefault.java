package com.java.data.controller.validator;

import javax.validation.GroupSequence;

@GroupSequence({UserCreate.class, UserUpdate.class})
public interface UserSequenceExcludeDefault {
}
