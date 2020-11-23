package com.java.util.javautil.patterns.decoratorpattern.coffeebar.decorator;


import com.java.util.javautil.patterns.decoratorpattern.coffeebar.BaseDrink;

/**
 * 酱油
 * @author yihur
 */
public class Soy extends BatchingBase {

    public Soy(BaseDrink obj) {
        super(obj);
        super.setDescription("Soy");
        super.setPrice(1.5f);
    }

}

