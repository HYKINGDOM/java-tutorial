package com.java.data.infrastucture.dao.mapper;

import com.java.data.domain.User;
import com.java.data.infrastucture.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * @author HY
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {


    @Mappings({
            @Mapping(target = "emailAddress", source = "userEmail")
    })
    UserEntity toUserEntity(User user);
}
