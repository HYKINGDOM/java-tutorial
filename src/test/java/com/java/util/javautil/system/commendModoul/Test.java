package com.java.util.javautil.system.commendModoul;

/**
 * @author yihur
 * @description c
 * @date 2019/4/3
 */
public class Test {

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command cmd = new MyCommand(receiver);
        Invoker invoker = new Invoker(cmd);
        invoker.action();
    }
}
