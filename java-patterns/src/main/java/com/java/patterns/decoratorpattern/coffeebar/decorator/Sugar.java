package com.java.util.javautil.patterns.decoratorpattern.coffeebar.decorator;

/**
 * 配料糖
 *
 * @author yihur
 */
public class Sugar extends BatchingBase {

    public Sugar(BatchingBase batchingBase) {
        super(batchingBase);
        super.setDescription("Sugar");
        super.setPrice(1.0f);
    }
}
