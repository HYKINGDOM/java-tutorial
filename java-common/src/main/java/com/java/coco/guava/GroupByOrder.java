package com.java.coco.guava;

import com.google.common.collect.ArrayListMultimap;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author meta
 */
public class GroupByOrder {

    public static void main(String[] args) {

        List<Order> orderList = new ArrayList<>();
        orderList.add(
            new Order("001", "0002", Double.valueOf("1.0"), Date.from(Instant.now()), Date.from(Instant.now())));

        orderList.add(
            new Order("002", "0003", Double.valueOf("1.1"), Date.from(Instant.now()), Date.from(Instant.now())));

        orderList.add(
            new Order("003", "0004", Double.valueOf("1.2"), Date.from(Instant.now()), Date.from(Instant.now())));

        orderList.add(
            new Order("003", "0005", Double.valueOf("1.3"), Date.from(Instant.now()), Date.from(Instant.now())));
        GroupByOrder groupByOrder = new GroupByOrder();
        Map<String, List<Order>> stringListMap = groupByOrder.normalOrder(orderList);
        for (Map.Entry<String, List<Order>> stringListEntry : stringListMap.entrySet()) {
            System.out.println(stringListEntry.getKey());
            System.out.println(Arrays.toString(stringListEntry.getValue().toArray()));
        }
        System.out.println("=========================");
        ArrayListMultimap<String, Order> stringOrderArrayListMultimap = groupByOrder.guavaGroup(orderList);
        for (String s : stringOrderArrayListMultimap.keySet()) {
            System.out.println(stringOrderArrayListMultimap.get(s));
        }
    }

    public Map<String, List<Order>> normalOrder(List<Order> orderList) {
        Map<String, List<Order>> userOrdersMap = new HashMap<>();
        for (Order order : orderList) {
            String userId = order.getUserId();
            if (userOrdersMap.containsKey(userId)) {
                userOrdersMap.get(userId).add(order);
            } else {
                List<Order> orders = new ArrayList<>();
                orders.add(order);
                userOrdersMap.put(userId, orders);
            }
        }
        return userOrdersMap;
    }

    public ArrayListMultimap<String, Order> guavaGroup(List<Order> orderList) {
        ArrayListMultimap<String, Order> arrayListMultimap = ArrayListMultimap.create();
        orderList.forEach(e -> arrayListMultimap.put(e.getUserId(), e));
        return arrayListMultimap;
    }

    @Data
    @AllArgsConstructor
    public static class Order {

        private String userId;
        private String orderId;
        private Double price;
        private Date createTime;
        private Date updateTime;
    }
}
