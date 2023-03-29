package com.java.func;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionTest {


    @Test
    public void test_Function() {

        Predicate<String> dataPredicate = e -> e.contains("Z");


        Consumer<String> dataConsumer = e -> {
            e = e.toUpperCase(Locale.ROOT);
            System.out.println(e);
        };


        Function<String, String> stringFunction = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return StringUtils.compareIgnoreCase(o1.substring(0, 1), o2.substring(0, 1));
            }
        };

        List<String> stringList1 = getListSupplier()
                .get()
                .stream()
                .filter(dataPredicate)
//                .peek(dataConsumer)
                .map(stringFunction)
                .sorted(stringComparator)
//                .peek(dataConsumer)
                .collect(Collectors.toList());


        stringList1 = getListSupplier()
                .get()
                .stream()
                .filter(dataPredicate)
//                .peek(dataConsumer)
                .map(stringFunction)
                .sorted(stringComparator)
                .peek(dataConsumer)
                .collect(Collectors.toList());

        System.out.println(stringList1.size());
        for (String s : stringList1) {
            System.out.println(s);
        }
    }

    private static Supplier<List<String>> getListSupplier() {
        return () -> RanDomCollectUtils.randomCollectList(13, 100);
    }


    private static Supplier<List<Map<String, String>>> getMapListSupplier() {
        return () -> RanDomCollectUtils.randomCollectListMap(8, 100, 5);
    }

    @Test
    public void test_Function01() {

        Predicate<Map<String, String>> dataPredicate = e -> {
            for (String str : e.keySet()) {
                if (str.contains("Z")) {
                    return true;
                }
            }
            return false;
        };


        Consumer<Map<String, String>> dataConsumer = e -> {
            for (Map.Entry<String, String> stringStringEntry : e.entrySet()) {
                if (stringStringEntry.getKey().contains("Z")) {
                    String value = stringStringEntry.getValue();
                    stringStringEntry.setValue(value.toUpperCase());
                }
            }
        };


        Consumer<Map<String, String>> printConsumer = e -> {
            for (Map.Entry<String, String> stringStringEntry : e.entrySet()) {
                System.out.println(stringStringEntry.getKey());
                System.out.println(stringStringEntry.getValue());
                System.out.println("-".repeat(40));
            }
        };


        Function<String, String> stringFunction = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return StringUtils.compareIgnoreCase(o1.substring(0, 1), o2.substring(0, 1));
            }
        };

//        List<String> stringList1 = getMapListSupplier()
//                .get()
//                .stream()
//                .filter(dataPredicate)
////                .peek(dataConsumer)
//                .map(stringFunction)
//                .sorted(stringComparator)
////                .peek(dataConsumer)
//                .collect(Collectors.toList());

        List<Map<String, String>> mapList = getMapListSupplier().get();
        mapList
                .stream()
                .filter(dataPredicate)
//                .peek(dataConsumer)
                .forEach(printConsumer);

        System.out.println(mapList.size());
        for (Map<String, String> stringStringMap : mapList) {
            System.out.println(stringStringMap);

        }
    }

}
