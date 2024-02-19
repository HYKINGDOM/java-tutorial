package com.java.tutorial.project.controller;

import com.java.tutorial.project.entity.Order;
import com.java.tutorial.project.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: double-cache
 * @author: Hydra
 * @create: 2022-03-13 13:32
 **/
@Slf4j
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("get/{id}")
    public Order get(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("update")
    public void updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
    }

    @PutMapping("/put")
    public void updatePatchOrder(@RequestBody Order order) {
        log.info(order.toString());
        orderService.updatePutOrder(order);
    }

    @GetMapping("get2")
    public Order get2(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        return orderService.getOrderByIdAndStatus(id, status);
    }

    @DeleteMapping("del")
    public void del(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
    }
}
