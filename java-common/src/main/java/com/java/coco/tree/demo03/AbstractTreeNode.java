package com.java.coco.tree.demo03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ace on 2017/6/12.
 * @author hy852
 */
public abstract class AbstractTreeNode {

    protected Integer id;

    protected Integer parentId;

    protected List<AbstractTreeNode> children = new ArrayList<>();

    /**
     *
     * @param childrenNode 全部节点
     * @return 构建好的树结构
     */
    public abstract <T extends AbstractTreeNode> List<T> createTreeNode(List<AbstractTreeNode> childrenNode);


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AbstractTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<AbstractTreeNode> children) {
        this.children = children;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void add(AbstractTreeNode node){
        children.add(node);
    }


}

