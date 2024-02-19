package com.java.patterns.ChainofResponsibility;

/**
 * @author HY
 */
public class BusinessLogicHandler extends Handler<User> {

    @Override
    public void doHandler(User user) {
        System.out.println("蜗牛好棒！");
    }
}