package com.java.util.javautil.recursion;

public class RecursionFunction {

    public int forLoopSelfOperation(int n) {
        if (n == 1) {
            return n;
        } else {
            return n * forLoopSelfOperation(n - 1);
        }
    }


    public int forLoopSelfNotOperation(int n, int result) {
        if (n == 1) {
            return n;
        } else {
            return forLoopSelfNotOperation(n - 1, n * result);
        }
    }


    public static void main(String[] args) {
        RecursionFunction recursionFunction = new RecursionFunction();
        long timeMillis01 = System.currentTimeMillis();
        recursionFunction.forLoopSelfOperation(10000);
        long timeMillis02 = System.currentTimeMillis();
        System.out.println(timeMillis02 - timeMillis01);
        recursionFunction.forLoopSelfNotOperation(10000, 1);
        long timeMillis03 = System.currentTimeMillis();
        System.out.println(timeMillis03 - timeMillis02);
    }
}
