package com.java.util.javautil.utils.bean.mapstruct;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainMapperTest {

    @Test
    public void testSimpleMap() {
        StudentVo studentVo = StudentVo.builder()
                .school("清华大学")
                .userId("ams")
                .userName("AI码师")
                .age(27)
                .address("合肥")
                .emailAddress("123@qq.com")
                .nikeName("bryce")
                .build();
        StudentDto studentDto = MainMapper.INSTANCE.studentVo2Dto(studentVo);
        System.out.println(studentDto);
    }

}