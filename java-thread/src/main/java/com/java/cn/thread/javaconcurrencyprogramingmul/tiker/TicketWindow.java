package com.java.cn.thread.javaconcurrencyprogramingmul.tiker;

public class TicketWindow extends Thread {

    private static final int Maximum = 500;
    private static int index = 1;
    private final String name;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= Maximum) {
            System.out.println("柜台：" + name + " 当前号码是：" + (index++));
        }
    }
}
