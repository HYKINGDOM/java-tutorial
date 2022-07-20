package com.java.patterns.decoratorpattern.coffeebar.coffee;


import com.java.patterns.decoratorpattern.coffeebar.BaseDrink;

/**
 * 饮品中咖啡基类
 *
 * @author yihur
 */
public class CoffeeBase extends BaseDrink {

    @Override
    public float cost() {
        return super.getPrice();
    }


}
