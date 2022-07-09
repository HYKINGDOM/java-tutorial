package com.java.coco.utils.bean.mapstruct;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-09T22:58:21+0800",
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

        studentDto.setEmail( vo.getEmailAddress() );
        studentDto.setNike_Name( vo.getNikeName() );
        studentDto.setUserName( vo.getUserName() );
        studentDto.setUserId( vo.getUserId() );
        studentDto.setAddress( vo.getAddress() );
        studentDto.setSchool( vo.getSchool() );
        studentDto.setAge( vo.getAge() );

        return studentDto;
    }
}
