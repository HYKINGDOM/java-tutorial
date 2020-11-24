package com.java.util.javautil.patterns.strategypattern.advancedduck;

import com.java.util.javautil.patterns.strategypattern.advancedduck.ducks.*;
import com.java.util.javautil.patterns.strategypattern.advancedduck.flybehavior.NoFlyBehavior;
import com.java.util.javautil.patterns.strategypattern.advancedduck.quackbehavior.NoQuackBehavior;
import com.java.util.javautil.patterns.strategypattern.advancedduck.swimbehavior.NoSwimBehavior;

/**
 * 基于策略模式的实践
 *
 * @author Administrator
 */
public class StimulateDuck {

    public static void main(String[] args) {

        BaseDuck mGreenHeadBaseDuck = new GreenHeadBaseDuck();
        BaseDuck mRedHeadBaseDuck = new RedHeadBaseDuck();
        BaseDuck stoneBaseDuck = new StoneBaseDuck();
        BaseDuck plasticBaseDuck = new PlasticBaseDuck();

        mGreenHeadBaseDuck.display();
        mGreenHeadBaseDuck.fly();
        mGreenHeadBaseDuck.quack();
        mGreenHeadBaseDuck.swim();
        System.out.println("=========================");

        mRedHeadBaseDuck.display();
        mRedHeadBaseDuck.fly();
        mRedHeadBaseDuck.quack();
        mRedHeadBaseDuck.swim();
        System.out.println("======修改红头鸭属性======");
        mRedHeadBaseDuck.display();
        mRedHeadBaseDuck.setFlyBehavior(new NoFlyBehavior());
        mRedHeadBaseDuck.fly();
        mRedHeadBaseDuck.setQuackBehavior(new NoQuackBehavior());
        mRedHeadBaseDuck.quack();
        mRedHeadBaseDuck.setmSwimBehavior(new NoSwimBehavior());

        System.out.println("=========================");
        stoneBaseDuck.display();
        stoneBaseDuck.fly();
        stoneBaseDuck.quack();
        stoneBaseDuck.swim();

        System.out.println("=========================");
        plasticBaseDuck.display();
        plasticBaseDuck.fly();
        plasticBaseDuck.quack();
        plasticBaseDuck.swim();

    }

}
