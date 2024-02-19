package com.java.vavr.function;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;

public class FunctionVavr {

    public static void main(String[] args) {

        Function2<Integer, Integer, Integer> sum2 = (integer, integer2) -> integer + integer2;
        Function1<Integer, Integer> add1 = sum2.apply(2);

        Function3<Integer, Integer, Integer, Integer> sum3 =
            (integer, integer2, integer3) -> integer + integer2 + integer3;
        Function1<Integer, Integer> add2 = sum3.apply(2, 3);

    }
}
