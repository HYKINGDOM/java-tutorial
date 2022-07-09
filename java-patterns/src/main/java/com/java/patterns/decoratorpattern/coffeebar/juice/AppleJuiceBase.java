package com.java.util.javautil.patterns.decoratorpattern.coffeebar.juice;


import com.java.util.javautil.patterns.decoratorpattern.coffeebar.BaseDrink;

/**
 * 苹果汁
 * @author yihur
 */
public class AppleJuiceBase extends JuiceBase {

    public AppleJuiceBase(BaseDrink obj){
        super(obj);
        super.setDescription("AppleJuice");
        super.setPrice(5.1f);
    }
}
