package com.java.util.javautil.patterns.decoratorpattern.coffeebar.coffee;

/**
 * Long Black 咖啡
 * @author yihur
 */
public class LongBlack extends CoffeeBase {

    public LongBlack(Integer amount) {
        super.setDescription("**LongBlack**");
        super.setAmount(amount);
        super.setPrice(6.0f);
    }

}

