package com.java.util.javautil.patterns.decoratorpattern.coffeebar.coffee;


import com.java.util.javautil.patterns.decoratorpattern.coffeebar.BaseDrink;

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
