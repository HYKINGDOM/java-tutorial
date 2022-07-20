package com.java.patterns.decoratorpattern.coffeebar.juice;


import com.java.patterns.decoratorpattern.coffeebar.BaseDrink;

/**
 * 果汁基类
 *
 * @author yihur
 */
public class JuiceBase extends BaseDrink {

    private BaseDrink obj;

    public JuiceBase(BaseDrink obj) {
        this.obj = obj;
    }


    /**
     * 价格
     *
     * @return
     */
    @Override
    public float cost() {
        return super.getPrice() + obj.cost();
    }

    /**
     * 名称
     *
     * @return
     */
    @Override
    public String getDescription() {
        return super.description + ": " + super.getPrice() + "\n" + obj.getDescription() ;
    }
}
