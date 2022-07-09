package com.java.func;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaFunction {


    public static void main(String[] args) {
        Change change01 = new Change(100L, "test01", 21, true);
        Change change02 = new Change(110L, "test02", 22, true);
        Change change03 = new Change(130L, "test03", 23, false);
        List<Change> changeList01 = new ArrayList<>();
        changeList01.add(change01);
        changeList01.add(change02);
        changeList01.add(change03);

        JavaFunction javaFunction = new JavaFunction();
        boolean compareTesting = javaFunction.compareTesting(changeList01, changeList01, Change::getId);
        System.out.println(compareTesting);
    }


    public <T> boolean compareTesting(List<T> list1, List<T> list2, Function<T, Long> mapping) {

        Sets.SetView<Long> difference = Sets.difference(list1.stream().map(mapping).collect(Collectors.toSet()), list2.stream().map(mapping).collect(Collectors.toSet()));

        return difference.isEmpty();

    }
}
