package com.java.vavr.api;

import io.vavr.control.Try;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;
import static io.vavr.Predicates.instanceOf;

public class APIVavr {

    public String bmiFormat(double height, double weight) {
        double bmi = weight / (height * height);
        return Match(bmi).of(
            // else if (bmi < 18.5)
            Case($(v -> v < 18.5), () -> "18.5"),
            // else if (bmi < 25)
            Case($(v -> v < 25), () -> "25"),
            // else if (bmi < 30)
            Case($(v -> v < 30), () -> "30"),
            // else
            Case($(), () -> "30+"));

    }

    public void testMatchFailure() {
        var res = Try.of(() -> {
            throw new RuntimeException();
        });
        Match(res).of(
            // 匹配成功情况
            Case($Success($()), r -> "Success"),
            // 匹配异常为 RuntimeException
            Case($Failure($(instanceOf(RuntimeException.class))), r -> "RuntimeException"),
            // 匹配异常为 IllegalStateException
            Case($Failure($(instanceOf(IllegalStateException.class))), r -> "IllegalStateException"),
            // 匹配异常为 NullPointerException
            Case($Failure($(instanceOf(NullPointerException.class))), r -> "NullPointerException"),
            // 匹配其余失败的情况
            Case($Failure($()), r -> "Failure"));
    }
}
