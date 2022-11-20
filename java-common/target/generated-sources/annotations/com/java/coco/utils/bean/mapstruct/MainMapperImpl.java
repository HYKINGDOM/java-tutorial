package com.java.coco.utils.bean.mapstruct;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-20T16:16:07+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class MainMapperImpl implements MainMapper {

    @Override
    public StudentDto studentVo2Dto(StudentVo vo) {
        if ( vo == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        return studentDto;
    }
}
