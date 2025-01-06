package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.OrderItem;
import com.java.tutorial.project.domain.Page;
import com.java.tutorial.project.domain.PageRequest;

/**
 * @author meta
 */
public interface OrderItemService {


    Page<OrderItem> findPage(PageRequest pageRequest);
}
