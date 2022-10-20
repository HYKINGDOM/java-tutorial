package com.java.tutorial.project;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;


@Component
public class UseBeanFactoryAwareDemo implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    public void testDemo(){
        Object myBeanPostProcessor = beanFactory.getBean("myBeanPostProcessor");
        System.out.println(myBeanPostProcessor.toString());
    }
}
