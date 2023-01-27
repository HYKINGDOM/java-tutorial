package com.java.tutorial.project.service;


import com.java.tutorial.project.template.Factory;
import com.java.tutorial.project.template.Handler;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ResourceServiceImpl implements ResourceService{


    @Resource
    private Handler invokeStrategy;


    @Override
    public void startHandle() {


        invokeStrategy = Factory.getInvokeStrategy("");
        invokeStrategy.AA("");

    }
}
