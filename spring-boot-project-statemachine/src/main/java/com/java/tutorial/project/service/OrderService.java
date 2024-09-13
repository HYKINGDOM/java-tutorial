package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.Order;

import java.util.Map;

public interface OrderService {

    Order create();

    Order pay(long id);

    Order deliver(long id);

    Order receive(long id);

    Map<Long, Order> getOrders();
}
