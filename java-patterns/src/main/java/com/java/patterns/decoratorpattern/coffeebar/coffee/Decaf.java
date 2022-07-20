package com.java.patterns.decoratorpattern.coffeebar.coffee;

/**
 * 低咖(脱咖啡因咖啡)
 *
 * @author yihur
 */
public class Decaf extends CoffeeBase {

    public Decaf(Integer amount) {
        super.setDescription("**Decaf**");
        super.setAmount(amount);
        super.setPrice(3.0f);
    }

}
