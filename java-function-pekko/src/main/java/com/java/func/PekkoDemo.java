package com.java.func;

import org.apache.pekko.PekkoVersion;
import org.apache.pekko.dispatch.ForkJoinExecutorConfigurator;
import org.apache.pekko.util.LineNumbers;

public class PekkoDemo {

    public static void main(String[] args) {
        LineNumbers.SourceFile sourceFile = new LineNumbers.SourceFile("D:\\project\\me\\new\\java-tutorial\\java-function-pekko\\pom.xml");

        sourceFile.productIterator().foreach(obj -> {

            System.out.println(obj.toString());
            return obj + "";

        });
    }
}
