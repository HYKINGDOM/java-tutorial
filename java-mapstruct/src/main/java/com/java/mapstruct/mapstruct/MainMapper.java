package com.java.mapstruct.mapstruct;


import org.mapstruct.Mapper;

/**
 * @author HY
 */

@Mapper(componentModel = "spring")
public interface MainMapper {


    StudentDto studentVo2Dto(StudentVo vo);
}
