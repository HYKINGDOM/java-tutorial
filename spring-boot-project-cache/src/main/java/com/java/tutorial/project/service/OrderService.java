package com.java.tutorial.project.service;


import com.java.tutorial.project.entity.Order;

/**
 * @author HY
 */
public interface OrderService {

    Order getOrderById(Long id);

    Order updateOrder(Order order);

    void deleteOrder(Long id);

    Order getOrderByIdAndStatus(Long id,Integer status);

}
