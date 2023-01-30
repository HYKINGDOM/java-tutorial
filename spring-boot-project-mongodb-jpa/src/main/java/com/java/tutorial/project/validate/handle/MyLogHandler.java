package com.java.tutorial.project.validate.handle;

import com.java.tutorial.project.validate.HandleResult;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class MyLogHandler implements MyHandler {

    @Override
    public @NonNull HandleResult<String> doHandle(String param) {
        log.info("MyLogHandler hello {} !", param);
        return HandleResult.doNextResult();
    }
}
