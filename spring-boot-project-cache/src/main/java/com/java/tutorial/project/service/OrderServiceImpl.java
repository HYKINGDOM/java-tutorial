package com.java.tutorial.project.service;


import com.java.tutorial.project.annotation.CacheType;
import com.java.tutorial.project.annotation.DoubleCache;
import com.java.tutorial.project.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author HY
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Override
    @DoubleCache(cacheName = "order", key = "#id",
            type = CacheType.FULL)
    public Order getOrderById(Long id) {
        return Order.builder().id(id).build();
    }

    @Override
    @DoubleCache(cacheName = "order", key = "#order.id",
            type = CacheType.PUT)
    public Order updateOrder(Order order) {

        return order;
    }

    @Override
    @DoubleCache(cacheName = "order", key = "#id",
            type = CacheType.DELETE)
    public void deleteOrder(Long id) {

    }

    @Override
    @DoubleCache(cacheName = "order", key = "#id")
    public Order getOrderByIdAndStatus(Long id, Integer status) {
        return Order.builder().id(id).status(status).build();
    }
}
