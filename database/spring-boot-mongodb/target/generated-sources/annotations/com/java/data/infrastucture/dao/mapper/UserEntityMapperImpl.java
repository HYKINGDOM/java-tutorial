package com.java.data.infrastucture.dao.mapper;

import com.java.data.domain.User;
import com.java.data.infrastucture.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-09T21:08:41+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class UserEntityMapperImpl implements UserEntityMapper {

    @Override
    public UserEntity toUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmailAddress( user.getUserEmail() );
        userEntity.setId( user.getId() );
        userEntity.setUserName( user.getUserName() );
        userEntity.setPassWord( user.getPassWord() );

        return userEntity;
    }
}
