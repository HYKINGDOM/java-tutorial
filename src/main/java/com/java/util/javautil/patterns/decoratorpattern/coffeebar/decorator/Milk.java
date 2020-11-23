package com.java.util.javautil.patterns.decoratorpattern.coffeebar.decorator;


import com.java.util.javautil.patterns.decoratorpattern.coffeebar.BaseDrink;

/**
 * 牛奶
 * @author yihur
 */
public class Milk extends BatchingBase {

    public Milk(BaseDrink obj) {
        super(obj);
        super.setDescription("Milk");
        super.setPrice(2.0f);
    }

}

