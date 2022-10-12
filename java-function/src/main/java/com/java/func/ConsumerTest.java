package com.java.func;


import java.util.function.Consumer;
import java.util.stream.Stream;

public class ConsumerTest {


    public static void main(String[] args) {
        Consumer<String> stringConsumer = (s) -> System.out.println(s.length());
        Stream.of("ab", "abc", "a", "abcd").forEach(stringConsumer);
    }
}
