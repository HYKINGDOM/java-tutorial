package com.java.tutorial.project.infrastucture.mapper;


import com.java.tutorial.project.controller.response.UserResponse;
import com.java.tutorial.project.domain.User;
import com.java.tutorial.project.infrastucture.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponse toUserResponse(User user);


    UserResponse toUserResponse(UserEntity userEntity);
}
