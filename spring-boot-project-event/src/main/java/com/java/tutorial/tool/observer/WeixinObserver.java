package com.java.tutorial.tool.observer;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.java.tutorial.tool.event.Account;

/**
 * 微信支付观察者
 */
public class WeixinObserver {
    // 标记当前订阅者是线程安全的，支持并发接收消息
    @AllowConcurrentEvents
    @Subscribe
    public void pay(Account account) {
        if (account.getName().equalsIgnoreCase("WEIXIN")) {
            System.out.println("微信 >>>>>> 已付款");
            System.out.println(account.getMessage());
        }
    }
}
