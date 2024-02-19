package com.java.data.mapper;

import com.java.data.controller.response.UserResponse;
import com.java.data.domain.User;
import com.java.data.infrastucture.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponse toUserResponse(User user);

    UserResponse toUserResponse(UserEntity userEntity);
}
