package com.java.coco.scs.manager;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

/**
 * @author yihur
 * @description 树结构转换成list
 * @date 2019/4/9
 */
public class TreeSwitchList {


    private List<Map<String, Object>> selectAllTreeNode() {
        List<Map<String, Object>> resultReturn = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("id", "100");
        map.put("parentID", "10");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "200");
        map.put("parentID", "100");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "201");
        map.put("parentID", "100");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "300");
        map.put("parentID", "200");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "301");
        map.put("parentID", "201");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "400");
        map.put("parentID", "300");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "401");
        map.put("parentID", "301");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "500");
        map.put("parentID", "400");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "501");
        map.put("parentID", "401");
        resultReturn.add(map);

        return resultReturn;
    }


    public static void main(String[] args) {
        //将树转化为Map结构
        String result = "Tree.class".toString();//抽象写法，先将树转为String
        JSONObject jsonObject = JSONObject.parseObject(result);
        //获取Map形式的数据。
        Map<String, Object> objectMap = JSONObject.toJavaObject(jsonObject, Map.class);
        //该处需要给该树的根节点添加id和pid（例如id=UUID.randomUUID().toString()，pid=0）
        objectMap.put("pid", "0");
        objectMap.put("id", UUID.randomUUID().toString());
        //广度遍历解析树
        List<Map<String, Object>> platList = breadthFirst(objectMap);
        for (Map<String, Object> plat : platList) {
            System.out.println(plat.toString());
        }

    }


    /***
     * 树转化为List 广度优先遍历
     * @param root
     * @return
     */
    public static List<Map<String, Object>> breadthFirst(Map<String, Object> root) {
        List<Map<String, Object>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        Queue<Map<String, Object>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Map<String, Object> tree = queue.poll();
            String pid = (String) tree.get("id");
            List<Map<String, Object>> children = (List<Map<String, Object>>) tree.get("children");
            for (Map<String, Object> child : children) {
                child.put("pid", pid);
                child.put("id", UUID.randomUUID().toString());
                queue.offer(child);
            }
            Map<String, Object> plat = new HashMap<>();
            plat.put("name", tree.get("name"));
            plat.put("pid", tree.get("pid"));
            plat.put("id", tree.get("id"));
            lists.add(plat);
        }
        return lists;
    }


    @Test
    public void test_queue_list() {
        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue<String> queue = new LinkedList<>();
        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("poll=" + queue.poll()); //返回第一个元素，并在队列中删除
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("element=" + queue.element()); //返回第一个元素
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("peek=" + queue.peek()); //返回第一个元素
        for (String q : queue) {
            System.out.println(q);
        }
    }

}
