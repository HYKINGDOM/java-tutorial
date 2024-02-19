package com.java.coco.tree.demo03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeClent {

    public static void main(String[] args) {
        AbstractTreeNode depNode00 = new DepInfo(1, "中国", 0);
        AbstractTreeNode depNode01 = new DepInfo(2, "美国", 0);
        AbstractTreeNode depNode1 = new DepInfo(12, "山东", 1);
        AbstractTreeNode depNode2 = new DepInfo(3, "陕西", 1);
        AbstractTreeNode depNode3 = new DepInfo(4, "山西", 1);
        AbstractTreeNode depNode4 = new DepInfo(5, "江苏", 1);
        AbstractTreeNode depNode5 = new DepInfo(6, "浙江", 1);

        AbstractTreeNode depNode07 = new DepInfo(7, "西安", 3);
        AbstractTreeNode depNode08 = new DepInfo(8, "大同", 4);
        AbstractTreeNode depNode09 = new DepInfo(9, "宝鸡", 3);
        AbstractTreeNode depNode10 = new DepInfo(10, "安康", 3);
        AbstractTreeNode depNode11 = new DepInfo(11, "杭州", 6);

        AbstractTreeNode depNode20 = new DepInfo(13, "富平", 7);
        AbstractTreeNode depNode21 = new DepInfo(14, "彬县", 7);
        AbstractTreeNode depNode22 = new DepInfo(15, "高粱", 7);
        AbstractTreeNode depNode23 = new DepInfo(16, "周至", 7);
        AbstractTreeNode depNode24 = new DepInfo(17, "乾县", 7);

        AbstractTreeNode depNode30 = new DepInfo(18, "富平", 13);
        AbstractTreeNode depNode31 = new DepInfo(19, "彬县", 13);
        AbstractTreeNode depNode32 = new DepInfo(20, "高粱", 13);
        AbstractTreeNode depNode33 = new DepInfo(21, "周至", 13);
        AbstractTreeNode depNode34 = new DepInfo(22, "乾县", 13);

        List<AbstractTreeNode> depAllNode = new ArrayList<>();
        depAllNode.add(depNode00);
        depAllNode.add(depNode01);
        depAllNode.add(depNode1);
        depAllNode.add(depNode2);
        depAllNode.add(depNode3);
        depAllNode.add(depNode4);
        depAllNode.add(depNode5);
        depAllNode.add(depNode07);
        depAllNode.add(depNode08);
        depAllNode.add(depNode09);
        depAllNode.add(depNode10);
        depAllNode.add(depNode11);

        depAllNode.add(depNode20);
        depAllNode.add(depNode21);
        depAllNode.add(depNode22);
        depAllNode.add(depNode23);
        depAllNode.add(depNode24);

        depAllNode.add(depNode30);
        depAllNode.add(depNode31);
        depAllNode.add(depNode32);
        depAllNode.add(depNode33);
        depAllNode.add(depNode34);

        for (int i = 0; i < 100000; i++) {
            int nextInt = new Random().nextInt(22) + 1;
            int ran2 = (int)(Math.random() * (20000 - 20) + 20);
            depAllNode.add(new DepInfo(ran2, "杭州" + i, nextInt));
        }

        DepInfo depInfo = new DepInfo();
        long timeMillis01 = System.currentTimeMillis();
        List<DepInfo> treeNode = depInfo.createTreeNode(depAllNode);
        long timeMillis02 = System.currentTimeMillis();

        System.out.println(timeMillis02 - timeMillis01);

        for (DepInfo node : treeNode) {
            System.out.println(node.getDepName());
        }

    }

}
