package com.java.coco.tree.demo02;

import com.java.coco.tree.demo02.entity.EntityTreeComponent;

public class TestCompositeStack {

    public static void main(String[] args) {

    }


    public static void testStack(EntityTreeComponent menuComponent) {
        CompositeIterator compositeIterator = new CompositeIterator(menuComponent.createIterator());
        while (compositeIterator.hasNext()) {
            EntityTreeComponent menuComponent1 = (EntityTreeComponent) compositeIterator.next();
        }
    }
}
