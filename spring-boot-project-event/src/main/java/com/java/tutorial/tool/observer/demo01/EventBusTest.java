package com.java.tutorial.tool.observer.demo01;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.java.tutorial.tool.event.Account;
import lombok.val;

import java.util.Date;
import java.util.concurrent.Executors;

public class EventBusTest {
    // 回调通知
    public static void notifyObserver() {
        EventBus bus = new EventBus();
        Man man = new Man();
        bus.register(man);
        bus.post(new Account("富安娜", 2.1, new Date()));
    }

    public static void notifyAsynObserver() {
        val executorService = Executors.newCachedThreadPool();
        AsyncEventBus bus = new AsyncEventBus(executorService);
        Man man = new Man();
        bus.register(man);
        bus.post(new Account("富安娜", 2.1, new Date()));
    }

    public static void main(String[] args) {
        notifyAsynObserver();
    }

}
