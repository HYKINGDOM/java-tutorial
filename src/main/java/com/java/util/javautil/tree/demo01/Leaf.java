package com.java.util.javautil.tree.demo01;

public class Leaf implements Component {

    @Override
    public void operation() {
        System.out.println("i am leaf");
    }

    @Override
    public void add(Component component) {
        throw new RuntimeException("leaf do not support add");
    }

    @Override
    public void remove(Component component) {
        throw new RuntimeException("leaf do not support remove");

    }

    @Override
    public void getChild(int i) {
        throw new RuntimeException("leaf do not support getChild");

    }
}

