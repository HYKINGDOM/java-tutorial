package com.java.util.javautil.patterns.decoratorpattern.coffeebar;

/**
 *
 * 饮品抽象类
 *
 * @author yihur
 */
public abstract class BaseDrink {

    /**
     * 名称
     */
    public String description;

    /**
     * 数量
     */
    public Integer amount = 1;

    /**
     * 价格
     */
    private float price = 0f;


    /**
     * 设置饮品名称
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取饮品名称
     *
     * @return
     */
    public String getDescription() {
        return description + ": " + this.getPrice() + "\n";
    }

    /**
     * 获取价格
     *
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     * 计算价格价格
     *
     * @param price
     */
    public void setPrice(float price) {
        this.price = price * amount;
    }

    /**
     * 计算数量
     *
     * @param amount
     */
    public void setAmount(Integer amount) {
        if (amount >= 1) {
            this.amount = amount;
        }
    }


    /**
     * 总价
     *
     * @return
     */
    public abstract float cost();

}
