package com.java.util.javautil.patterns.decoratorpattern.coffeebar;


import com.java.util.javautil.patterns.decoratorpattern.coffeebar.coffee.Cappuccino;
import com.java.util.javautil.patterns.decoratorpattern.coffeebar.coffee.Decaf;
import com.java.util.javautil.patterns.decoratorpattern.coffeebar.decorator.Chocolate;
import com.java.util.javautil.patterns.decoratorpattern.coffeebar.decorator.Milk;
import com.java.util.javautil.patterns.decoratorpattern.coffeebar.juice.OrangeJuiceBase;

/**
 * 装饰器模式实践
 *
 * @author yihur
 */
public class CoffeeBar {

    public static void main(String[] args) {
        BaseDrink order;
        System.out.println("****************");
        order = new Cappuccino(1);
        //一杯低咖
        order = new Decaf(2);
        //加一份牛奶
        order = new Milk(order);
        //加一份巧克力
        order = new Chocolate(order);
        //再加一份巧克力
        order = new Chocolate(order);
        //再来份橙汁
        order = new OrangeJuiceBase(order);
        System.out.println("order1 desc:" + "\n" + order.getDescription());
        System.out.println("order1 price:" + order.cost());
        System.out.println("****************");
    }
}
