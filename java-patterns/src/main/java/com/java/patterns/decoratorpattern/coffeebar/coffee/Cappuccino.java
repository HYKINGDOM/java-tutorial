package com.java.patterns.decoratorpattern.coffeebar.coffee;

/**
 * 卡布奇诺咖啡
 *
 * @author yihur
 */
public class Cappuccino extends CoffeeBase {

    public Cappuccino(Integer amount) {
        super.setDescription("**Cappuccino**");
        super.setAmount(amount);
        super.setPrice(20.0f);
    }
}
