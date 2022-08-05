package com.java.coco.utils.bean.mapstruct;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * @author HY
 */

@Mapper(componentModel = "spring")
public interface MainMapper {


    StudentDto studentVo2Dto(StudentVo vo);
}
