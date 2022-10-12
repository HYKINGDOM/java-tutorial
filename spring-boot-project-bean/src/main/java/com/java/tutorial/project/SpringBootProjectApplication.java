package com.java.tutorial.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootProjectApplication {

    public static void main(String[] args) {
        // 得到上下文对象，并启动 Spring Boot 项目
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootProjectApplication.class, args);
        // 获取 Bean
        BeanLifeComponent component = context.getBean(BeanLifeComponent.class);
        // 使用 Bean
        component.use();
        // 停止 Spring Boot 项目
        context.close();

    }

}
