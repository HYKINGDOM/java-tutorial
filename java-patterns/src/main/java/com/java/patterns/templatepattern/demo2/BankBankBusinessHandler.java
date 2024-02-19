package com.java.patterns.templatepattern.demo2;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;

public class BankBankBusinessHandler extends AbstractBankBusinessHandler {

    public void saveVip(BigDecimal amount) {
        execute(() -> "saveVipNumber-00: " + RandomUtils.nextInt(), a -> System.out.println("saveVipNumber " + amount));
    }

    @Override
    public void save(BigDecimal amount) {
        execute(() -> "saveNumber-00: " + RandomUtils.nextInt(), a -> System.out.println("saveNumber " + amount));
    }

    public void saveReservation(BigDecimal amount) {
        execute(() -> "saveReservationNumber-00: " + RandomUtils.nextInt(),
            a -> System.out.println("saveReservationNumber " + amount));
    }

}
