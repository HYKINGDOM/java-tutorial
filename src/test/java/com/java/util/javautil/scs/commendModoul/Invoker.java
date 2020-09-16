package com.java.util.javautil.scs.commendModoul;

/**
 * @author yihur
 * @description c
 * @date 2019/4/3
 */
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action(){
        command.exe();
    }
}
