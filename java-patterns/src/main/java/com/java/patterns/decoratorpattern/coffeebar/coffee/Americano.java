package com.java.patterns.decoratorpattern.coffeebar.coffee;

/**
 * 美式咖啡
 * @author yihur
 */
public class Americano extends CoffeeBase {

    public Americano(Integer amount) {
        super.setDescription("**Americano**");
        super.setAmount(amount);
        super.setPrice(8.0f);
    }
}
