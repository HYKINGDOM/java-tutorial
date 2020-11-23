package com.java.util.javautil.patterns.strategypattern.advancedduck.ducks;


import com.java.util.javautil.patterns.strategypattern.advancedduck.flybehavior.FlyBehavior;
import com.java.util.javautil.patterns.strategypattern.advancedduck.quackbehavior.QuackBehavior;
import com.java.util.javautil.patterns.strategypattern.advancedduck.swimbehavior.SwimBehavior;

/**
 * 鸭子基类
 *
 * @author yihur
 */
public abstract class BaseDuck {

    /**
     * 飞行动作接口
     */
    FlyBehavior mFlyBehavior;

    /**
     * 鸭子叫声接口
     */
    QuackBehavior mQuackBehavior;

    /**
     * 游泳接口
     */
    SwimBehavior mSwimBehavior;


    public BaseDuck() {

    }

    /**
     * 种类
     */
    public abstract void display();


    /**
     * 基本飞行行为
     */
    public void fly() {
        mFlyBehavior.fly();
    }

    /**
     * 设置新的飞行行为
     *
     * @param flyBehavior
     */
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        mFlyBehavior = flyBehavior;
    }

    /**
     * 基本的叫声属性
     */
    public void quack() {
        mQuackBehavior.quack();
    }

    /**
     * 设置新的叫声属性
     *
     * @param quackBehavior
     */
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        mQuackBehavior = quackBehavior;
    }


    /**
     * 鸭子基本的额游泳属性
     */
    public void swim() {
        mSwimBehavior.swim();
    }


    /**
     * 设置新的游泳行为
     *
     * @param swimBehavior
     */
    public void setmSwimBehavior(SwimBehavior swimBehavior) {
        mSwimBehavior = swimBehavior;
    }

}
