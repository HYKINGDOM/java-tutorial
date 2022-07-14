package com.java.cn.thread.parallel;

import java.util.List;

public class ParallelStream {

    public static void main(String[] args) {
        List<String> strings = List.of("qw", "123", "3e");

        strings.parallelStream().forEach(System.out::println);
        strings.stream().parallel().forEach(System.out::println);
    }
}
