package com.java.tutorial.project.controller;

import com.java.coco.common.Result;
import com.java.tutorial.project.domain.Order;
import com.java.tutorial.project.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Slf4j
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/testOrderStatusChange")
    public Result testOrderStatusChange() {
        orderService.create();
        orderService.create();

        orderService.pay(2L);
        orderService.deliver(2L);
        orderService.receive(2L);

        orderService.pay(1L);
        orderService.deliver(1L);
        orderService.receive(1L);
        log.info("全部订单状态：{}", orderService.getOrders());
        return Result.success();
    }

    @RequestMapping("/createOrder")
    public Result<Order> createOrder() {
        return Result.success(orderService.create());
    }

    @RequestMapping("/payOrder")
    public Result<Order> payOrder(@RequestParam("id") Long id) {
        return Result.success(orderService.pay(id));
    }

    @RequestMapping("/deliverOrder")
    public Result<Order> deliverOrder(@RequestParam("id") Long id) {
        return Result.success(orderService.deliver(id));
    }

    @RequestMapping("/receiveOrder")
    public Result<Order> receiveOrder(@RequestParam("id") Long id) {
        return Result.success(orderService.receive(id));
    }

    @RequestMapping("/getOrder")
    public Result<Order> getOrder(@RequestParam("id") Long id) {
        return Result.success(orderService.getOrders().get(id));
    }

}


