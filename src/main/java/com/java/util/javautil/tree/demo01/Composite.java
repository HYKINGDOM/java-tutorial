package com.java.util.javautil.tree.demo01;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private List<Component> componentList;

    public Composite() {
        this.componentList = new ArrayList<>();
    }

    /**
     * 循环遍历容器内容所有构件，并对其进行操作
     */
    @Override
    public void operation() {
        for (Component c : componentList) {
            c.operation();
        }
    }

    @Override
    public void add(Component component) {
        componentList.add(component);
    }

    @Override
    public void remove(Component component) {
        componentList.remove(component);
    }

    @Override
    public void getChild(int i) {
        componentList.get(i);
    }
}
