package com.java.util.javautil.guava;

import com.google.common.collect.ArrayListMultimap;

import java.time.Instant;
import java.util.*;

/**
 * @author HY
 */
public class GroupByOrder {

    public static void main(String[] args) {

        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("001", "0002", Double.valueOf("1.0"), Date.from(Instant.now()), Date.from(Instant.now())));

        orderList.add(new Order("002", "0003", Double.valueOf("1.1"), Date.from(Instant.now()), Date.from(Instant.now())));

        orderList.add(new Order("003", "0004", Double.valueOf("1.2"), Date.from(Instant.now()), Date.from(Instant.now())));

        orderList.add(new Order("003", "0005", Double.valueOf("1.3"), Date.from(Instant.now()), Date.from(Instant.now())));
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


    public static class Order {

        private String userId;
        private String orderId;
        private Double price;
        private Date createTime;
        private Date updateTime;

        public Order(String userId, String orderId, Double price, Date createTime, Date updateTime) {
            this.userId = userId;
            this.orderId = orderId;
            this.price = price;
            this.createTime = createTime;
            this.updateTime = updateTime;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "userId='" + userId + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", price=" + price +
                    ", createTime=" + createTime +
                    ", updateTime=" + updateTime +
                    '}';
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }
    }
}
