package com.java.patterns.templatepattern.demo1;

import org.apache.commons.lang3.RandomUtils;

public abstract class AbstractBusinessHandler {

    /**
     * 模板方法
     */

    public final void execute() {

        getNumber();

        handle();

        judge();

    }

    /**
     * 取号
     *
     */
    private void getNumber() {

        System.out.println("number-00" + RandomUtils.nextInt());

    }

    /**
     * 办理业务
     */
    public abstract void handle(); //抽象的办理业务方法，由子类实现

    /**
     * 评价
     */
    private void judge() {

        System.out.println("give a praised");

    }

}
