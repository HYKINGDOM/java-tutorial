package com.java.tutorial.tool.event;

import lombok.Data;

import java.util.Date;

@Data
public class Account {

    private String name;
    private double amount;
    private Date date;

    public Account(String name, double amount, Date date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("账户：").append(this.name).append(", ");
        sb.append("金额：").append(amount).append(", ");
        sb.append("日期：").append(date);
        return sb.toString();
    }
}
