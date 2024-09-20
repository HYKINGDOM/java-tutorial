package com.java.mapstruct.mapstruct;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author HY
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(AbstractStudentMappingDecorator.class)
public interface StudentMapping {

    StudentDto studentVo2Dto(StudentVo vo);

    StudentVo studentDto2Vo(StudentDto dto);

    List<StudentVo> studentDto2Vo(List<StudentDto> dtos);

    List<StudentDto> studentVo2Dto(List<StudentVo> vos);

}
