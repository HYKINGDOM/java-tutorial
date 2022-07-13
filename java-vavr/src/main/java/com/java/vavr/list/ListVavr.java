package com.java.vavr.list;

import io.vavr.collection.List;
import lombok.Data;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListVavr {

    public static void main(String[] args) {



    }

    private static void vavrListConvertJavaList() {
        var javaList = java.util.List.of(1, 2, 3, 4);
        java.util.List<Integer> javaList2 = List.ofAll(javaList)
                .filter(i -> i > 1)
                .map(i -> i * 2)
                .toJavaList();
    }

    private static void vavrFindList(java.util.List<Integer> javaList) {
        List<Integer> vavrList = List.of(2, 3, 4, 1, 3, 23, 34, 12);

        Predicate<Integer> predicate = i -> i == 4;

        Integer integer = vavrList.find(predicate).get();

        //java List 的实现需要使用filter + findFirst
        Integer integer1 = javaList.stream().filter(e -> e == 4).findFirst().get();
    }

    public java.util.Map<Integer, java.util.List<String>> userStatistic(java.util.List<User> users) {
        return users.stream()
                .filter(u -> u.getAge() >= 18)
                .collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getName, Collectors.toList())));
    }


    public io.vavr.collection.Map<Integer, io.vavr.collection.List<String>> userStatistic(io.vavr.collection.List<User> users) {
        return users.filter(u -> u.getAge() >= 18)
                .groupBy(User::getAge)
                .mapValues(usersGroup -> usersGroup.map(User::getName));
    }

    @Data
    public static class User {
        private Long id;
        private String name;
        private Integer age;

    }
}
