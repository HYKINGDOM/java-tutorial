package com.java.patterns.strategypattern.advancedduck.swimbehavior;

/**
 * 不会游泳
 *
 * @author yihur
 */
public class NoSwimBehavior implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("~~no swim~~");
    }
}
