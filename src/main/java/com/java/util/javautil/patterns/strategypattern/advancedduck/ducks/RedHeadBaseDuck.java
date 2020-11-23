package com.java.util.javautil.patterns.strategypattern.advancedduck.ducks;


import com.java.util.javautil.patterns.strategypattern.advancedduck.flybehavior.GoodFlyBehavior;
import com.java.util.javautil.patterns.strategypattern.advancedduck.quackbehavior.GeGeQuackBehavior;
import com.java.util.javautil.patterns.strategypattern.advancedduck.swimbehavior.BadSwimBehavior;

/**
 * 红头鸭
 *
 * @author Administrator
 */
public class RedHeadBaseDuck extends BaseDuck {


    /**
     * 红头鸭所具有的属性
     */
    public RedHeadBaseDuck() {
        mFlyBehavior = new GoodFlyBehavior();
        mQuackBehavior = new GeGeQuackBehavior();
        mSwimBehavior = new BadSwimBehavior();
    }

    @Override
    public void display() {
        System.out.println("**RedHead**");
    }


}
