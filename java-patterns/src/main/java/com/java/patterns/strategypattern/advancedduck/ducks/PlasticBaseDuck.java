package com.java.patterns.strategypattern.advancedduck.ducks;


import com.java.patterns.strategypattern.advancedduck.flybehavior.NoFlyBehavior;
import com.java.patterns.strategypattern.advancedduck.quackbehavior.GaGaQuackBehavior;
import com.java.patterns.strategypattern.advancedduck.swimbehavior.NoSwimBehavior;

/**
 * 塑料的鸭子
 *
 * @author yihur
 */
public class PlasticBaseDuck extends BaseDuck {


    public PlasticBaseDuck() {
        mQuackBehavior = new GaGaQuackBehavior();
        mFlyBehavior = new NoFlyBehavior();
        mSwimBehavior = new NoSwimBehavior();
    }


    @Override
    public void display() {
        System.out.println("**PlasticBaseDuck**");
    }

    @Override
    public String getDuckName() {
        return this.getClass().getSimpleName();
    }
}
