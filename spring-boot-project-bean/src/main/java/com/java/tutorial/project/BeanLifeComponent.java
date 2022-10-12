package com.java.tutorial.project;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author HY
 */
@Component
public class BeanLifeComponent implements BeanNameAware {

    public void setBeanName(String s) {
        System.out.println("执行 BeanName 的通知方法");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("执行初始化方法");
    }

    public void use() {
        System.out.println("使用 Bean");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("执行销毁方法");
    }
}
