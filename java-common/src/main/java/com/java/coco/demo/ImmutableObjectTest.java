package com.java.coco.demo;

public class ImmutableObjectTest {

    public static void main(String[] args) {
        Integer integer = 1;
        ImmutableObjectTest immutableObjectTest = new ImmutableObjectTest();
        immutableObjectTest.sumInteger(integer);
        immutableObjectTest.sumInteger(integer);
        immutableObjectTest.sumInteger(integer);
        System.out.println(integer);

        System.out.println("==============================");
        TestInteger testInteger = new TestInteger();
        System.out.println(testInteger.getNum());

        testInteger.setNum(testInteger.getNum() + 1);
        System.out.println(testInteger.getNum());
        testInteger.setNum(testInteger.getNum() + 1);
        System.out.println(testInteger.getNum());
        testInteger.setNum(testInteger.getNum() + 1);
        System.out.println(testInteger.getNum());

    }

    public Integer sumInteger(Integer integer) {

        integer = integer + 1;
        System.out.println(integer);
        return integer;
    }

    static final class TestInteger {
        private int num = 1;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

}
