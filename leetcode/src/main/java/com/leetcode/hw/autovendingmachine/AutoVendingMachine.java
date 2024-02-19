package com.leetcode.hw.autovendingmachine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AutoVendingMachine {

    /**
     * 名称和数量
     */
    private static Map<String, Integer> GOODS_NUMBER = new HashMap<>();

    /**
     * 名称和单价
     */
    private static Map<String, Integer> GOODS_PRICE = new HashMap<>();

    private static Map<Integer, Integer> CURRENCY_NUMBERS = new HashMap<>();

    private static List<Integer> CURRENCY = new ArrayList<>();

    private static int balance = 0;

    static {
        //初始化商品数量为0
        GOODS_NUMBER.put("A1", 0);
        GOODS_NUMBER.put("A2", 0);
        GOODS_NUMBER.put("A3", 0);
        GOODS_NUMBER.put("A4", 0);
        GOODS_NUMBER.put("A5", 0);
        GOODS_NUMBER.put("A6", 0);

        GOODS_PRICE.put("A1", 2);
        GOODS_PRICE.put("A2", 3);
        GOODS_PRICE.put("A3", 4);
        GOODS_PRICE.put("A4", 5);
        GOODS_PRICE.put("A5", 8);
        GOODS_PRICE.put("A6", 6);

        CURRENCY.add(1);
        CURRENCY.add(2);
        CURRENCY.add(5);
        CURRENCY.add(10);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String next = scanner.next();
            String startOperatedStr = next.substring(0, 1);
            String[] split = next.split(" ");

            switch (startOperatedStr) {
                case "r":
                    initMethod(split);
                    break;
                case "p":
                    coinOperated(split);
                    break;
                case "b":
                    buyGoods(split);
                    break;
                case "c":
                    sendBack(split);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 2.4 退币 命令格式：c 功能说明： （1） 如果投币余额等于0的情况下，输出“E009:Work failure”； （2） 如果投币余额大于0的情况下，按照 退币原则 进行“找零”，输出退币信息； 约束说明： （1）
     * 系统在任意阶段都可以退币； （2） 退币方式必须按照 退币原则 进行退币； 输出说明：如果退币成功，按照 退币原则 输出退币信息。 例，退5元钱币: 命令 输出 c; 1 yuan coin number=0 2 yuan
     * coin number=0 5 yuan coin number=1 10 yuan coin number=0
     *
     * @param split
     */
    private static void sendBack(String[] split) {

        if (balance == 0) {
            System.out.println("E009:Work failure\t");
        }
    }

    /**
     * 2.3 购买商品 命令格式：b 商品名称 功能说明： （1） 如果购买的商品不在商品列表中，输出“E006:Goods does not exist”； （2） 如果所购买的商品的数量为0，输出“E007:The goods
     * sold out”； （3） 如果投币余额小于待购买商品价格，输出“E008:Lack of balance”； （4） 如果购买成功，输出“S003:Buy success,balance=X”； 约束说明： （1）
     * 一次购买操作仅能购买一件商品，可以多次购买； （2） 同等条件下，错误码的优先级：E006 > E007 > E008； 输出说明： 如果购买成功，输出“S003:Buy success,balance=X”。
     *
     * @param split
     */
    private static void buyGoods(String[] split) {
        String goodsNum = split[1];
        if (!GOODS_NUMBER.containsKey(goodsNum)) {
            System.out.println("E006:Goods does not exist\t");
        } else if (GOODS_NUMBER.get(goodsNum) == 0) {
            System.out.println("E007:The goods sold out\t");
        } else if (GOODS_PRICE.get(goodsNum) > balance) {
            System.out.println("E008:Lack of balance\t");
        } else {
            Integer integer = GOODS_NUMBER.get(goodsNum);
            GOODS_NUMBER.put(goodsNum, integer - 1);
            Integer currencyNumber = CURRENCY_NUMBERS.get(balance);
            CURRENCY_NUMBERS.put(balance, currencyNumber + 1);
            System.out.println("S003:Buy success,balance=X\t");
        }

    }

    private static void coinOperated(String[] split) {
        int coinInput = Integer.parseInt(split[1]);

        int sumPrice = CURRENCY_NUMBERS.get(1) + CURRENCY_NUMBERS.get(2) + 2;
        Integer sumNumber = GOODS_PRICE.values().stream().reduce(Integer::sum).orElse(0);

        if (!CURRENCY.contains(coinInput)) {
            System.out.println("E002:Denomination error\t");
        } else if (sumPrice < coinInput) {
            System.out.println("E003:Change is not enough, pay fail\t");
        } else if (sumNumber.equals(0)) {
            System.out.println("E005:All the goods sold out\t");
        } else {
            balance = balance + coinInput;
            System.out.println("S002:Pay success,balance=" + coinInput + "\t");
        }
    }

    private static void initMethod(String[] split) {
        String goodsNumber = split[1];
        String[] goodsNumbers = goodsNumber.trim().split("-");
        initGoodsNumber(goodsNumbers);

        String currencyNumber = split[2];
        String[] currencyNumbers = currencyNumber.trim().split("-");
        initCurrencyNumber(currencyNumbers);
        System.out.println("S001:Initialization is successful\t");
    }

    private static void initCurrencyNumber(String[] strings) {
        int flag = 0;

        for (int i = 0; i < strings.length; i++) {
            Integer money = Integer.valueOf(strings[flag]);
            CURRENCY_NUMBERS.put(CURRENCY.get(i), money);
        }
    }

    private static void initGoodsNumber(String[] strings) {
        int flag = 0;
        for (String name : GOODS_NUMBER.keySet()) {
            GOODS_NUMBER.put(name, Integer.valueOf(strings[flag]));
            flag++;
        }
    }
}
