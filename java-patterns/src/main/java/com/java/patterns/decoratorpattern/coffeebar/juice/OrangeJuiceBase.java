package com.java.util.javautil.patterns.decoratorpattern.coffeebar.juice;


import com.java.util.javautil.patterns.decoratorpattern.coffeebar.BaseDrink;

/**
 * 橘子汁
 *
 * @author yihur
 */
public class OrangeJuiceBase extends JuiceBase {

    public OrangeJuiceBase(BaseDrink obj) {
        super(obj);
        super.setDescription("OrangeJuiceBase");
        super.setPrice(6.1f);
    }
}
