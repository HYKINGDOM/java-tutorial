package com.java.tutorial.tool.observer.demo01;

public class Woman implements HouseWork {
    @Override
    public void dry() {
        System.out.println("可以晾衣服了");
    }
}
