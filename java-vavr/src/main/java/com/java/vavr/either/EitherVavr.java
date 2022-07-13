package com.java.vavr.either;

import io.vavr.control.Either;

public class EitherVavr {


    //Either 它表示某个值可能为两种类型中的一种，比如下面的 compute() 函数的 Either 返回值代表结构可能为 Exception 或 String。通常用 right 代表正确的值
    private Either<Exception, Integer> compute(int marks) {
        if (marks < 85) {
            return Either.left(new Exception());
        } else {
            return Either.right(marks);
        }
    }

    public void test() {

        // 异常值
        Either<Exception, Integer> computeLeft = compute(90);
        if (computeLeft.isLeft()) {
            Exception exception = computeLeft.getLeft();
        }

        // 正确值
        Either<Exception, Integer> computeRight = compute(24);
        if (computeRight.isRight()) {
            Integer integer = computeRight.get();
        }
    }
}
