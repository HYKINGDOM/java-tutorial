package com.java.patterns.strategypattern.baseduck;

/**
 * 绿头鸭
 *
 * @author Administrator
 */
public class GreenHeadBaseDuck extends BaseDuck {

    @Override
    public void display() {
        System.out.println("**GreenHead**");
    }

    @Override
    public void fly() {
        System.out.println("~~bad fly~~");
    }

    @Override
    public void quack() {
        System.out.println("~~gege~~");
    }
}