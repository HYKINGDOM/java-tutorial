package com.leetcode.title.myPow;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyPowSolution {

    public double myPow(double x, int n) {

        if (n == 0 || Double.valueOf(x).equals(1.0)) {
            return 1.0;
        }
        int num = n;
        double result = x;
        if (n < 0) {
            num = Math.abs(n);
            result = 1 / result;
            x = 1 / x;
        }

        for (int i = 0; i < num - 1; i++) {
            result = result * x;
        }
        BigDecimal bg = new BigDecimal(result).setScale(5, RoundingMode.DOWN);
        return bg.doubleValue();
    }
}
