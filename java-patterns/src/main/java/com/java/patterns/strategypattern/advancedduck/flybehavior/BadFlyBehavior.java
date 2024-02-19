package com.java.patterns.strategypattern.advancedduck.flybehavior;

/**
 * 实现不正常的飞行行为
 *
 * @author Administrator
 */
public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("--BadFly--");
    }
}
