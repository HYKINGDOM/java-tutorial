package com.java.patterns.strategypattern.baseduck;

/**
 * 鸭子的种类
 *
 * @author Administrator
 */
public abstract class BaseDuck {

    public BaseDuck() {

    }

    /**
     * 种类
     */
    public abstract void display();

    public void fly() {
        System.out.println("~~im fly~~");
    }

    public void quack() {
        System.out.println("~~gaga~~");
    }

    public void swim() {
        System.out.println("~~im swim~~");
    }

}
