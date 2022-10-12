package com.java.patterns.templatepattern.demo2;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractBankBusinessHandler {

    private void getNumber(String number) {
        System.out.println("getNumber-00: " + number);
    }

    public void save(BigDecimal amount) {
        execute(() -> "save: " + RandomUtils.nextInt(), a -> System.out.println("save: " + amount));
    }

    private void judge() {
        System.out.println("give a praised");
    }


    protected void execute(Supplier<String> supplier, Consumer<BigDecimal> consumer) {

        String number = supplier.get();

        getNumber(number);

        if (number.startsWith("vip")) {
            //Vip号分配到VIP柜台
            System.out.println("Assign To Vip Counter");
        } else if (number.startsWith("reservation")) {
            //预约号分配到专属客户经理
            System.out.println("Assign To Exclusive Customer Manager");
        } else {
            //默认分配到普通柜台
            System.out.println("Assign To Usual Manager");
        }
        consumer.accept(null);

        judge();
    }
}
