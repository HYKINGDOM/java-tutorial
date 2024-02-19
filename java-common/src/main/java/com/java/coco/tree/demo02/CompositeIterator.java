package com.java.coco.tree.demo02;

import com.java.coco.tree.demo02.entity.EntityTreeComponent;
import com.java.coco.tree.demo02.entity.MenuInfo;

import java.util.Iterator;
import java.util.Stack;

/**
 * 自定义组合模式的组合节点的专属迭代器 CompositeIterator
 *
 * @author hy852
 */
public class CompositeIterator implements Iterator {

    private Stack<Iterator> stack = new Stack<>();

    /**
     * 把要遍历的 Menu 组合的迭代器 iterator 传入，menuComponents.iterator() 被传入一个 stack 中保存位置
     *
     * @param iterator
     */
    public CompositeIterator(Iterator iterator) {
        stack.push(iterator);
    }

    /**
     * 当客户端需要取得下一个元素的时候，先判断是否存在下一个元素
     *
     * @return
     */
    @Override
    public Object next() {
        if (hasNext()) {
            // 仅查看当前的栈顶元素——迭代器，不出栈
            Iterator iterator = stack.peek();
            // 使用该栈顶的迭代器，取出要遍历的组合的元素
            EntityTreeComponent component = (EntityTreeComponent)iterator.next();
            if (component instanceof MenuInfo) {
                // 如果取出的元素仍然是菜单，那需要继续遍历它，故要记录它的位置，把它的迭代器取出来
                // 调用 component.createIterator() 返回 CompositeIterator，这个 CompositeIterator 仍然包含一个自己的 stack，继续存入栈中
                stack.push(component.createIterator());
            }
            return component;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        // 如果栈是空，直接返回 false
        if (stack.empty()) {
            return false;
        } else {
            // 仅查看当前的栈顶元素——迭代器，不出栈
            Iterator iterator = stack.peek();
            // 判断当前的顶层元素是否还有下一个元素，如果栈空了，就说明当前顶层元素没有下一个元素，返回 false，此处判断为 true
            if (!iterator.hasNext()) {
                stack.pop(); // 如果当前栈顶元素，没有下一个元素了，就把当前栈顶元素出栈，递归的继续判断下一个元素
                return hasNext();
            } else { // 否则表示还有下一个元素，直接返回 true
                return true;
            }
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
