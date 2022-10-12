package com.java.tutorial.project;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("beanLifeComponent".equals(beanName)) {
            System.out.println("执行初始化前置方法");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("beanLifeComponent".equals(beanName)) {
            System.out.println("执行初始化后置方法");
        }
        return bean;
    }
}
