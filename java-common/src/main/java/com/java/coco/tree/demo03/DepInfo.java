package com.java.coco.tree.demo03;

import java.util.ArrayList;
import java.util.List;

import static com.java.coco.tree.demo03.TreeUtil.buildByTreeNode;

/**
 * @author hy852
 */
public class DepInfo extends AbstractTreeNode {

    private String depName;

    public DepInfo() {
    }

    public DepInfo(Integer id, String depName, Integer parentId) {
        super.id = id;
        this.depName = depName;
        super.parentId = parentId;
        super.children = new ArrayList<>();
    }

//    @Override
//    public List<AbstractTreeNode> createTreeNode(List<AbstractTreeNode> depChildren) {
//        return buildByTreeNode(depChildren);
//    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public <T extends AbstractTreeNode> List<T> createTreeNode(List<AbstractTreeNode> childrenNode) {
        return (List<T>) buildByTreeNode(childrenNode);
    }
}
