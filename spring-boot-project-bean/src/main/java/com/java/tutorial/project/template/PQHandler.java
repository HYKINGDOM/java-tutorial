package com.java.tutorial.project.template;

import org.springframework.stereotype.Component;

@Component
public class PQHandler implements Handler {
    @Override
    public void AA(String nikeName) {
        //业务逻辑
        System.out.println("我是皮球");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("皮球", this);
    }
}
