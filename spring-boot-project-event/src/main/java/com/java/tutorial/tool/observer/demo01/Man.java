package com.java.tutorial.tool.observer.demo01;

import com.google.common.eventbus.Subscribe;
import com.java.tutorial.tool.event.Account;

public class Man {
    @Subscribe
    public void dry(Account sheet) {
        System.out.println("可以晾 " + sheet.getName() + " 床单了");
    }

}
