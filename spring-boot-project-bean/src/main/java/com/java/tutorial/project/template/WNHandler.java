package com.java.tutorial.project.template;

import org.springframework.stereotype.Component;

@Component
public class WNHandler implements Handler {

    @Override
    public void AA(String nikeName) {
        //业务逻辑
        System.out.println("我是蜗牛");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("蜗牛", this);
    }
}
