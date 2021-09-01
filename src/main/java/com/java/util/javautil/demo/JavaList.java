package com.java.util.javautil.demo;

import com.google.common.collect.Lists;
import com.java.util.javautil.DDD.alibaba.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class JavaList {


    private static Map<Integer, List<LocalDateTime>> integerListMap = new HashMap<>();

    private static Map<Integer, List<LocalDateTime>> testDemo;


    public static void main(String[] args) {
        List<Long> longList = new ArrayList<>();
        longList.add(100L);
        longList.add(400L);
        longList.add(300L);
        longList.add(200L);
        longList.add(600L);
        //longList.add(500L);

        List<TestUser> userList = new ArrayList<>();

        userList.add(new TestUser(123, LocalDateTime.now()));
        userList.add(new TestUser(232, LocalDateTime.now().plusDays(1)));
        userList.add(new TestUser(232, LocalDateTime.now().plusDays(2)));
        userList.add(new TestUser(233, LocalDateTime.now().plusDays(2)));
        userList.add(new TestUser(124, LocalDateTime.now().plusDays(3)));
        userList.add(new TestUser(125, LocalDateTime.now().plusDays(4)));
        userList.add(new TestUser(126, LocalDateTime.now().plusDays(5)));
        userList.add(new TestUser(124, LocalDateTime.now().plusDays(6)));
        userList.add(new TestUser(124, LocalDateTime.now().plusDays(6)));
        userList.add(new TestUser(124, LocalDateTime.now().plusDays(6)));




        for (List<Long> longs : Lists.partition(longList, 1000)) {
            System.out.println(Arrays.toString(longs.toArray()));
            System.out.println("================");
        }

        Map<Integer, List<LocalDateTime>> objectListMap = userList.parallelStream()
                .collect(groupingBy(TestUser::getEmpId, mapping(TestUser::getLocalDateTime, toList())));
        for (Integer integer : objectListMap.keySet()) {
            for (LocalDateTime localDateTime : objectListMap.get(integer)) {
                System.out.println(localDateTime);
            }
            System.out.println("================================");
        }

        List<LocalDateTime> localDateTimes = integerListMap.get(0);
        List<LocalDateTime> localDateTimes1 = testDemo.get(0);

        System.out.println(integerListMap);
        System.out.println(testDemo);
    }


    static class TestUser {

        private Integer empId;

        private LocalDateTime localDateTime;


        public TestUser(Integer empId, LocalDateTime localDateTime) {
            this.empId = empId;
            this.localDateTime = localDateTime;
        }

        public Integer getEmpId() {
            return empId;
        }

        public void setEmpId(Integer empId) {
            this.empId = empId;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }
    }
}
