package com.java.util.javautil.tree.demo02;

import java.util.Iterator;

/**
 * 自定义组合模式的叶子节点的专属迭代器
 */
public class NullIterator implements Iterator {

    @Override
    public Object next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
