package com.leetcode.title;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DemoTest {

    private Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };

    @Test
    public void test_repeat_string() {

        List<String> strings = new ArrayList<>();
        strings.add("b");
        strings.add("c");
        strings.add("a");
        strings.add("d");

        for (String string : strings) {

            System.out.println(string);

            if (string.equals("a")) {
                strings = strings.stream().sorted(comparator).collect(Collectors.toList());

                strings.forEach(e-> System.out.println("test" +e));
            }

        }

    }

    @Test
    public void test_repeat_string_02() {

        List<String> strings = new ArrayList<>();
        strings.add("b");
        strings.add("c");
        strings.add("a");
        strings.add("d");

        for (String string : strings) {

            System.out.println(string);

            if (string.equals("a")) {

                Collections.sort(strings, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                strings.forEach(e-> System.out.println("test" +e));
            }

        }

    }
}
