package com.java.vavr.tryvavr;

import io.vavr.control.Try;

public class TryVavr {

    public static void main(String[] args) {

        //Try 是个接口， 具体的实现是 Success 或 Failure
        //
        Try.of(() -> 1 / 0).andThen(r -> System.out.println("and then " + r))
            .onFailure(error -> System.out.println("failure" + error.getMessage())).andFinally(() -> {
                System.out.println("finally");
            });
    }
}
