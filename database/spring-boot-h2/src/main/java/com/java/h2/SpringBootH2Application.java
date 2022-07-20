package com.java.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootH2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootH2Application.class, args);
    }

}
