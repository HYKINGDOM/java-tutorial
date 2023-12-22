package com.java.tutorial.project;

import com.java.tutorial.project.validate.handle.MyHandlerChain;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 单元测试
 */
@Slf4j
@SpringBootTest
class SpringBootProjectApplicationTests {

    @Autowired
    private MyHandlerChain handlerChain;

    @Test
    public void handleChain() {
        String result = handlerChain.handleChain("zzzzbw");
        log.info("handleChain result: {}", result);
    }

}