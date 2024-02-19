package com.java.patterns.strategypattern.advancedduck.flybehavior;

/**
 * 描述不能飞行的状态
 *
 * @author Administrator
 */
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("--NoFly--");
    }
}
