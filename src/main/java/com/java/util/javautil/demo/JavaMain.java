package com.java.util.javautil.demo;


import static com.java.util.javautil.demo.JavaStream.testDemo;

public class JavaMain {

    public static void main(String[] args) {
        JavaStream javaStream = new JavaStream();

        javaStream.testDemo();
        javaStream.testDemo(2);


        JavaStream.testDemo01(1, 2);


        testDemo("4");
    }
}
