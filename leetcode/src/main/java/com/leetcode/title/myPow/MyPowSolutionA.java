package com.leetcode.title.myPow;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyPowSolutionA {

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }


//    public double myPow(double x, int n) {
//        return quickPow(x, n);
//    }
//
//    double quickPow(double x, long n) {
//        if (Double.valueOf(x).equals(0.0)) {
//            return 0.0;
//        }
//        double tmp = n < 0 ? 1.0 / x : x, res = 1.0;
//        n = Math.abs(n);
//        while (n > 0) {
//            if ((n & 1) == 1) {
//                res *= tmp;
//            }
//            tmp *= tmp;
//            n >>= 1;
//        }
//        return res;
//    }

}
