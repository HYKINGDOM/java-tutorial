package com.java.patterns.strategypattern.advancedduck.quackbehavior;


/**
 * 咯咯叫的声音
 * @author Administrator
 */
public class GeGeQuackBehavior implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("__GeGe__");
    }

}
