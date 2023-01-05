package com.java.tutorial.project.controller.validator;


import javax.validation.GroupSequence;

@GroupSequence({UserCreate.class, UserUpdate.class})
public interface UserSequenceExcludeDefault {
}
