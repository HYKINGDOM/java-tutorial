package com.java.coco.tree.demo03;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ace on 2017/6/12.
 *
 * @author hy852
 */
public class TreeUtil {
    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static <T extends AbstractTreeNode> List<T> build(List<T> treeNodes, Object root) {

        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (Objects.equals(it.getParentId(), treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static <T extends AbstractTreeNode> List<T> buildByTreeNode(List<T> treeNodes) {
        //默认第一个节点为根节点,可以并列多个,可以在查询的时候进行排序处理
        T rootTreeNode = treeNodes.get(0);
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (rootTreeNode.getParentId().equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static <T extends AbstractTreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (Objects.equals(treeNode.getId(), it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}

