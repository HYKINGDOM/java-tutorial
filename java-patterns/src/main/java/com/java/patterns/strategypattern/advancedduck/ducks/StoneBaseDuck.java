package com.java.patterns.strategypattern.advancedduck.ducks;

import com.java.patterns.strategypattern.advancedduck.flybehavior.NoFlyBehavior;
import com.java.patterns.strategypattern.advancedduck.quackbehavior.NoQuackBehavior;
import com.java.patterns.strategypattern.advancedduck.swimbehavior.NoSwimBehavior;

/**
 * 石头做的鸭子
 *
 * @author Administrator
 */
public class StoneBaseDuck extends BaseDuck {

    /**
     * 石头鸭所具有的属性
     */
    public StoneBaseDuck() {
        mFlyBehavior = new NoFlyBehavior();
        mQuackBehavior = new NoQuackBehavior();
        mSwimBehavior = new NoSwimBehavior();
    }

    @Override
    public void display() {
        System.out.println("**StoneBaseDuck**");
    }

    @Override
    public String getDuckName() {
        return this.getClass().getSimpleName();
    }
}
