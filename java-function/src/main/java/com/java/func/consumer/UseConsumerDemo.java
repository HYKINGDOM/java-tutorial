package com.java.func.consumer;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class UseConsumerDemo {

    public static void main(String[] args) {

        Consumer<String> consumer = System.out::println;
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer);

        stream.forEach(UseConsumerDemo::listToString);

    }

    private static String listToString(String str) {

        return str.toUpperCase();
    }
}
