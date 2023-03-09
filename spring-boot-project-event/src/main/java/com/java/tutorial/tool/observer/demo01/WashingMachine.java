package com.java.tutorial.tool.observer.demo01;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine {

    private List<HouseWork> houseWork = new ArrayList<>();

    public void register(HouseWork work) {
        houseWork.add(work);
    }

    public void unregister(HouseWork work) {
        houseWork.remove(work);
    }

    public void notifyObserver() {
        for (HouseWork work : houseWork) {
            work.dry();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 洗衣机
        WashingMachine machine = new WashingMachine();
        // 女主人
        Woman woman = new Woman();
        // 洗衣机让女主人成为自己的观察者
        machine.register(woman);
        System.out.println("将衣服放到洗衣机。。。");
        System.out.println("买菜、遛娃中。。。");
        Thread.sleep(3000);
        // 通知观察者（女主人），衣服洗完了
        machine.notifyObserver();
    }

}
