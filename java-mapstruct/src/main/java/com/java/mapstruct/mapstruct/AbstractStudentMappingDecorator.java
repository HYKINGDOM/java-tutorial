package com.java.mapstruct.mapstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * @author meta
 */
@Slf4j
public class AbstractStudentMappingDecorator implements StudentMapping {

    @Autowired
    @Qualifier("delegate")
    private StudentMapping delegate;

    @Override
    public StudentDto studentVo2Dto(StudentVo vo) {
        return delegate.studentVo2Dto(vo);
    }

    @Override
    public StudentVo studentDto2Vo(StudentDto dto) {
        return delegate.studentDto2Vo(dto);
    }

    @Override
    public List<StudentVo> studentDto2Vo(List<StudentDto> dtos) {
        return delegate.studentDto2Vo(dtos);
    }

    @Override
    public List<StudentDto> studentVo2Dto(List<StudentVo> vos) {
        return delegate.studentVo2Dto(vos);
    }
}
