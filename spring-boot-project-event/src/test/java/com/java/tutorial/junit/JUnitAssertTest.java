package com.java.tutorial.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.Duration;

public class JUnitAssertTest {

    @Test
    void assertEqualsDemo() {
        int num = 10;
        Assertions.assertEquals(1, num, "不符合预期");
    }

    @Test
    void assertTrueDemo() {
        int num = 10;
        Assertions.assertTrue(num > 10, "不符合预期");
    }

    @Test
    void assertTimeoutDemo() {
        int num = 10;
        Assertions.assertTimeout(Duration.ofSeconds(3), new Executable() {
            @Override
            public void execute() throws Throwable {
                //代码块
                Thread.sleep(4000);
            }
        });
    }

}
