package com.java.vavr.lazy;

import io.vavr.Lazy;

public class LazyVavr {


    public static void main(String[] args) {
        Lazy<Double> lazy = Lazy.of(Math::random);
        // = false
        System.out.println(lazy.isEvaluated());

        // = 0.123 (random generated)
        System.out.println(lazy.get());

        // = true
        System.out.println(lazy.isEvaluated());

        // = 0.123 (memoized)
        System.out.println(lazy.get());
    }
}
