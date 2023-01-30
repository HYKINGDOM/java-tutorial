package com.java.tutorial.project.validate.handle;

import com.java.tutorial.project.validate.HandleResult;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 处理器2
 */
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
@Component
public class MyDefaultHandler implements MyHandler {

    @Override
    public @NonNull HandleResult<String> doHandle(String param) {
        log.info("MyDefaultHandler param is {}", param);
        return HandleResult.doCurrentResult("MyDefaultHandler");
    }
}

