package com.java.util.javautil.tree.demo02.entity;

import com.java.util.javautil.tree.demo02.CompositeIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hy852
 */
public class MenuInfo extends EntityTreeComponent {

    private Integer menuId;

    private Integer parentId;

    private String menuName;


    private List<EntityTreeComponent> entityTreeComponents = new ArrayList<>();

    @Override
    public Integer getId() {
        return getMenuId();
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    @Override
    public String getName() {
        return getMenuName();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


    public List<EntityTreeComponent> getEntityTreeComponents() {
        return entityTreeComponents;
    }

    public void setEntityTreeComponents(List<EntityTreeComponent> entityTreeComponents) {
        this.entityTreeComponents = entityTreeComponents;
    }

    @Override
    public void print() {
        System.out.print("\n" + getName());
        System.out.println("---------------------");
        // 使用了迭代器（迭代器模式和组合模式的有机结合），遍历菜单的菜单项
        Iterator iterator = entityTreeComponents.iterator();
        while (iterator.hasNext()) {
            // 打印这个节点包含的一切，print 可以兼顾两类节点，这是组合模式的特点
            EntityTreeComponent menuComponent = (EntityTreeComponent) iterator.next();
            menuComponent.print(); // 递归思想的应用
        }
    }

    @Override
    public Iterator createIterator() {
        return new CompositeIterator(entityTreeComponents.iterator());
    }


}
