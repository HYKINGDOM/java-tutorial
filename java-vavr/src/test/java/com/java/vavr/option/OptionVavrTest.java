package com.java.vavr.option;

import io.vavr.control.Option;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OptionVavrTest {

    @Test
    public void testWithJavaOptional() {
        // Java Optional
        var result = Optional.of("hello").map(str -> (String)null).orElseGet(() -> "world");

        // result = "world"
        assertThat(result).isEqualTo("word");
    }

    @Test
    public void testWithVavrOption() {
        // Vavr Option
        var result = Option.of("hello").map(str -> (String)null).getOrElse(() -> "world");

        // result = null
        assertThat(result).isNull();
    }

    @Test
    public void testVavrOption() {
        // option 直接转为 List
        List<String> result = Option.of("vavr hello world").map(String::toUpperCase).toJavaList();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.iterator().next()).isEqualTo("VAVR HELLO WORLD");

        // exists(Function)
        boolean exists = Option.of("ok").exists(str -> str.equals("ok"));
        Assertions.assertThat(exists).isTrue();

        // contains
        boolean contains = Option.of("ok").contains("ok");
        Assertions.assertThat(contains).isTrue();
    }

}