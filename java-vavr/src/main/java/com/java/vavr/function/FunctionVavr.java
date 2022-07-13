package com.java.vavr.function;

import io.vavr.Function1;
import io.vavr.Function2;

public class FunctionVavr {

    public static void main(String[] args) {

        Function2<Integer, Integer, Integer> sum = Integer::sum;
        Function1<Integer, Integer> add2 = sum.apply(2);


    }
}
