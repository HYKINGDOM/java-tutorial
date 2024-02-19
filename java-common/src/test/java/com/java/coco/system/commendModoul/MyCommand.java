package com.java.coco.system.commendModoul;

/**
 * @author yihur
 * @description c
 * @date 2019/4/3
 */
public class MyCommand implements Command {
    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        receiver.action();
    }
}
