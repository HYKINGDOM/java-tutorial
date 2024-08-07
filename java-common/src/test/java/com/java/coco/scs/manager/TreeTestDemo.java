package com.java.coco.scs.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yihur
 * @description 树结构遍历---已知子节点遍历子节点的所有叶子节点
 * @date 2019/4/1
 */
public class TreeTestDemo {

    private List<Map<String, Object>> bodyList;

    private List<Map<String, Object>> rootList;

    private List<Map<String, Object>> beanList;

    @BeforeEach
    public void before_new_list() {
        bodyList = new ArrayList<>();
        rootList = new ArrayList<>();
        beanList = new ArrayList<>();
    }

    /**
     * 所有节点
     *
     * @param
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author yihur
     * @date 2019/4/4
     */
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

    /**
     * 根节点 或 起始节点
     *
     * @param
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author yihur
     * @date 2019/4/4
     */
    private List<Map<String, Object>> selectRootTreeNode() {
        List<Map<String, Object>> resultReturn = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("id", "100");
        map.put("parentID", "10");
        resultReturn.add(map);
        map = new HashMap<>();

        map.put("id", "200");
        map.put("parentID", "100");
        resultReturn.add(map);

        return resultReturn;
    }

    @Test
    public void test_tree_node_by_current_node() {
        List<Map<String, Object>> rootList = selectRootTreeNode();
        bodyList = selectAllTreeNode();
        // newHashMapWithExpectedSize是guava中的方法,用于初始化一个特定大小的HashMap
        Map<String, String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
        rootList.forEach(beanTree -> getChild(beanTree, map));

        System.out.println(Arrays.toString(rootList.toArray()));
        beanList.addAll(rootList);
        System.out.println(Arrays.toString(beanList.toArray()));
        System.out.println(Arrays.toString(bodyList.toArray()));
    }

    /**
     * 方法描述
     *
     * @param beanTree
     * @param map
     * @return void
     * @author yihur
     * @date 2019/4/4
     */
    private void getChild(Map<String, Object> beanTree, Map<String, String> map) {
        List<Map<String, Object>> childList = Lists.newArrayList();
        bodyList.stream().filter(c -> !map.containsKey(c.get("id").toString()))
            .filter(c -> c.get("parentID").toString().equals(beanTree.get("id").toString())).forEach(c -> {
                map.put(c.get("id").toString(), c.get("parentID").toString());
                getChild(c, map);
                childList.add(c);
            });
        // 所有叶子结点不加childList参数,避免叶子节点带有该参数下,前端控件依然显示加号
        if (childList.size() != 0) {
            beanTree.put("childList", childList);
            beanList.addAll(childList);
        }
    }

}
