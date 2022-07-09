package com.java.coco.tree.demo01;

public class Client {
    public static void main(String[] args) {

        Component leaf = new Leaf();
        //操作叶子
        leaf.operation();


        Component composite = new Composite();
        composite.add(leaf);
        //操作容器
        composite.operation();

    }
}
