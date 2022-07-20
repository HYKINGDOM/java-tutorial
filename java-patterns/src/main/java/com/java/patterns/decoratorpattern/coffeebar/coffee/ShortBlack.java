package com.java.patterns.decoratorpattern.coffeebar.coffee;

/**
 * Short Black 咖啡
 * @author yihur
 */
public class ShortBlack extends CoffeeBase {

    public ShortBlack(Integer amount) {
        super.setDescription("**ShortBlack**");
        super.setAmount(amount);
        super.setPrice(5.0f);
    }

}
