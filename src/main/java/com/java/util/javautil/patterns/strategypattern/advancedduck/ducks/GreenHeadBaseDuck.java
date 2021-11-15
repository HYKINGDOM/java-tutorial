package com.java.util.javautil.patterns.strategypattern.advancedduck.ducks;


import com.java.util.javautil.patterns.strategypattern.advancedduck.flybehavior.BadFlyBehavior;
import com.java.util.javautil.patterns.strategypattern.advancedduck.quackbehavior.GaGaQuackBehavior;
import com.java.util.javautil.patterns.strategypattern.advancedduck.swimbehavior.GoodSwimBehavior;

/**
 * 绿头鸭
 *
 * @author Administrator
 */
public class GreenHeadBaseDuck extends BaseDuck {

    /**
     * 绿头鸭所具有的属性
     */
    public GreenHeadBaseDuck() {
        mFlyBehavior = new BadFlyBehavior();
        mQuackBehavior = new GaGaQuackBehavior();
        mSwimBehavior = new GoodSwimBehavior();
    }

    @Override
    public void display() {

    }

    @Override
    public String getDuckName() {
        return this.getClass().getSimpleName();
    }


}
