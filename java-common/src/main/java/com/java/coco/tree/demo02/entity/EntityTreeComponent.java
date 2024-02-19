package com.java.coco.tree.demo02.entity;

import java.util.Iterator;

/**
 * @author hy852
 * @Tree 结构
 */
public abstract class EntityTreeComponent {

    public void add(EntityTreeComponent entityTreeComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(EntityTreeComponent entityTreeComponent) {
        throw new UnsupportedOperationException();
    }

    public EntityTreeComponent getChild(Integer i) {
        throw new UnsupportedOperationException();
    }

    public Integer getId() {
        throw new UnsupportedOperationException();
    }

    public Integer getParentId() {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public void print() {
        throw new UnsupportedOperationException();
    }

    /**
     * 加上迭代器，这里直接使用 JDK 的迭代器
     *
     * @return
     */
    public abstract Iterator createIterator();
}
