package com.java.util.javautil.thread;

public class JavaStackDemo {

    public static void main(String[] args) {
        JavaStackDemo javaStackDemo = new JavaStackDemo();

        try {
            javaStackDemo.method1(1);
        }catch (Throwable throwable){
            System.out.println(throwable.getClass());
        }
    }


    private void method1(int i) {
        if (i == 0) {
            return;
        }
        method1(++i);
    }
}
