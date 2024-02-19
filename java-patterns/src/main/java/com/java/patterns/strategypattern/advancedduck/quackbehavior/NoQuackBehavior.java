package com.java.patterns.strategypattern.advancedduck.quackbehavior;

/**
 * 不会叫的声音
 *
 * @author Administrator
 */
public class NoQuackBehavior implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("__No Quack__");
    }

}
