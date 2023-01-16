package com.java.data.mapper;

import com.java.data.controller.response.UserResponse;
import com.java.data.domain.User;
import com.java.data.infrastucture.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T19:11:35+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.userAccountNum( user.getUserAccountNum() );
        userResponse.userName( user.getUserName() );
        userResponse.niceName( user.getNiceName() );
        userResponse.age( user.getAge() );
        userResponse.passWord( user.getPassWord() );
        userResponse.userEmail( user.getUserEmail() );

        return userResponse.build();
    }

    @Override
    public UserResponse toUserResponse(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.userName( userEntity.getUserName() );
        userResponse.passWord( userEntity.getPassWord() );

        return userResponse.build();
    }
}
