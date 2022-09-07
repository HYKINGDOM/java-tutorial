package com.leetcode.title.profit;

public class MaxProfitThree {

    public int maxProfit(int[] prices) {
        int result = 0;
        int min = prices[0];
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if ((min - prices[i]) < 0) {
                result = Math.max(result, (prices[i] - min));
                sum = sum + result;
                result = 0;
                min = prices[i];
            } else {
                min = Math.min(prices[i], min);
            }
        }
        return sum;
    }
}
