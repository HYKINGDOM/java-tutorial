package com.java.tutorial.project.validate.handle;

import com.java.tutorial.project.validate.HandleResult;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
@Order(3)
@Component
public class MyParamHandler implements MyHandler{
    @Override
    public @NonNull HandleResult<String> doHandle(String param) {
        log.info("MyParamHandler hello {} !", param);
        return HandleResult.doNextResult();
    }

}
