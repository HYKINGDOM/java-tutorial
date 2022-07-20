package com.java.patterns.strategypattern.baseduck;


/**
 *
 * 不利于扩展的的实践
 * @author Administrator
 */
public class TestMain {

    public static void main(String[] args) {
        GreenHeadBaseDuck greenHeadBaseDuck = new GreenHeadBaseDuck();
        RedHeadBaseDuck redHeadBaseDuck = new RedHeadBaseDuck();

        greenHeadBaseDuck.display();
        greenHeadBaseDuck.fly();
        greenHeadBaseDuck.quack();
        greenHeadBaseDuck.swim();

        redHeadBaseDuck.display();
        redHeadBaseDuck.fly();
        redHeadBaseDuck.quack();
        redHeadBaseDuck.swim();
    }
}
