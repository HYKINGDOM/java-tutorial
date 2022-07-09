package com.java.util.javautil.interview.hw;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HuaweiTest {

    @Test
    public void test_sale() {
        //Assert.assertEquals(140165471, getFibonacci(50));
        Assert.assertEquals(89, getRecursion(8));
    }


    //方法1：递归
    public int getFibonacci(int n) {
        if (n < 0)
            return -1;
        if (n <= 2)
            return n;
        return getFibonacci(n - 1) + getFibonacci(n - 3);
    }


    //解法2：迭代
    public int getRecursion(int n) {
        if (n < 0)
            return -1;
        if (n <= 2)
            return n;
        int temp1 = 1;
        int temp2 = 3;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = temp1 + temp2;
            temp1 = temp2;
            temp2 = result;
        }
        return result;
    }


    public List<String> returnListData(){
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("one");
        list.add("one");
        list.add("one");
        list.add("one");
        list.add("one");
        list.add("one");
        list.add("one");
        list.add("one");
        list.add("one");
        System.out.println("调用方法");
        return list;
    }

    @Test
    public void test_list(){
        for (String listDatum : returnListData()) {
            System.out.println(listDatum);
        }
    }

}
