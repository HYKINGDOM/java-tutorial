package com.java.patterns.templatepattern.demo2;

import java.math.BigDecimal;

public class TestMain1 {

    public static void main(String[] args) {
        BankBankBusinessHandler businessHandler = new BankBankBusinessHandler();
        businessHandler.saveVip(new BigDecimal("1000"));
    }
}
