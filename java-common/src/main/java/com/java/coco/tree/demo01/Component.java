package com.java.coco.tree.demo01;

public interface Component {

    void operation();

    /**
     *     向容器中添加构件
     */
    void add(Component component);

    /**
     * 从容器中删除构件
     * @param component
     */
    void remove(Component component);

    /**
     * 获取容器中的构件
     * @param i
     */
    void getChild(int i);
}
