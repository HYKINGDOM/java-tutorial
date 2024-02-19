package com.java.patterns.decoratorpattern.coffeebar.decorator;

import com.java.patterns.decoratorpattern.coffeebar.BaseDrink;

/**
 * 饮品的配料基类
 *
 * @author yihur
 */
public class BatchingBase extends BaseDrink {

    private BaseDrink obj;

    /**
     * @param obj
     */
    public BatchingBase(BaseDrink obj) {
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
        return super.description + ": " + super.getPrice() + "\n" + obj.getDescription();
    }

}
