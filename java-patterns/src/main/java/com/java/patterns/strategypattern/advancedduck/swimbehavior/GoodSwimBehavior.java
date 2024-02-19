package com.java.patterns.strategypattern.advancedduck.swimbehavior;

/**
 * 游泳技术很好
 *
 * @author yihur
 */
public class GoodSwimBehavior implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("~~Good swim~~");
    }
}
