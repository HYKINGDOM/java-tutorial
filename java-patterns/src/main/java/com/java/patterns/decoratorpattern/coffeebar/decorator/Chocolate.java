package com.java.patterns.decoratorpattern.coffeebar.decorator;

import com.java.patterns.decoratorpattern.coffeebar.BaseDrink;

/**
 * 巧克力
 *
 * @author yihur
 */
public class Chocolate extends BatchingBase {

    public Chocolate(BaseDrink obj) {
        super(obj);
        super.setDescription("Chocolate");
        super.setPrice(3.0f);
    }

}
