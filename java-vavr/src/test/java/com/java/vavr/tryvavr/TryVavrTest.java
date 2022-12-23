package com.java.vavr.tryvavr;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TryVavrTest {

    //通过 Try 的 recoverWith 方法，我们可以很优雅的实现降级策略
    @Test
    public void testTryWithRecover() {
        assertThat(testTryWithRecover(new NullPointerException())).isEqualTo("NPE");
        assertThat(testTryWithRecover(new IllegalStateException())).isEqualTo("IllegalState");
        assertThat(testTryWithRecover(new RuntimeException())).isEqualTo("Unknown");
    }

    private String testTryWithRecover(Exception e) {
        return (String) Try.of(() -> {
                    throw e;
                })
                .recoverWith(NullPointerException.class, Try.of(() -> "NPE"))
                .recoverWith(IllegalStateException.class, Try.of(() -> "IllegalState"))
                .recoverWith(RuntimeException.class, Try.of(() -> "Unknown"))
                .get();
    }





}