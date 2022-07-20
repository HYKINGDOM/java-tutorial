package com.java.patterns.decoratorpattern.coffeebar.coffee;

/**
 * 蒸馏咖啡(让蒸汽或开水通过磨碎的咖啡豆制成的浓咖啡),浓缩咖啡
 *
 * @author yihur
 */
public class Espresso extends CoffeeBase {

    public Espresso(Integer amount) {
        super.setDescription("**Espresso**");
        super.setAmount(amount);
        super.setPrice(4.0f);
    }

}
