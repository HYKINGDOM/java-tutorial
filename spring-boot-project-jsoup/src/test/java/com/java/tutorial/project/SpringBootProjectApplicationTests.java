package com.java.tutorial.project;

import com.java.tutorial.project.http.HttpClientUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootProjectApplicationTests {

    @Test
    void contextLoads() {

        HttpClientUtils httpClientUtils = new HttpClientUtils();
    }

}
