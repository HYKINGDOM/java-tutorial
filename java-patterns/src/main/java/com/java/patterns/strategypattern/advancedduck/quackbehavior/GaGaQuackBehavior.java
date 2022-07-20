package com.java.patterns.strategypattern.advancedduck.quackbehavior;


/**
 * 描述嘎嘎叫的声音
 * @author Administrator
 */
public class GaGaQuackBehavior implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("__GaGa__");
    }

}