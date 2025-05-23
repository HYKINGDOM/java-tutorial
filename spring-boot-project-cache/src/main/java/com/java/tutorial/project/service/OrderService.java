package com.java.tutorial.project.service;

import com.java.tutorial.project.entity.Order;

/**
 * @author meta
 */
public interface OrderService {

    Order getOrderById(Long id);

    Order updateOrder(Order order);

    Order updatePutOrder(Order order);

    void deleteOrder(Long id);

    Order getOrderByIdAndStatus(Long id, Integer status);

}
