package com.java.util.javautil.utils.bean.mapstruct;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * @author HY
 */

@Mapper(componentModel = "spring")
public interface MainMapper {


    MainMapper INSTANCE = Mappers.getMapper(MainMapper.class);

    @Mapping(source = "emailAddress", target = "email")
    @Mapping(source = "nikeName", target = "nike_Name")
    StudentDto studentVo2Dto(StudentVo vo);
}
