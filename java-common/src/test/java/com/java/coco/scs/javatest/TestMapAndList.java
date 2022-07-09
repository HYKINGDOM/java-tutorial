package com.java.coco.scs.javatest;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author yihur
 * @description 测试map, list拼接
 * @date 2019/4/19
 */
public class TestMapAndList {


    private Map<String, String> map;

    private List<Object> list;

    @Before
    public void before_map_new_hashMap() {
        //用guava的方法创建HashMap,这样初始化的时候需要制定HashMap的大小,一般大小指定为:(长度/0.75)+1
        //这样指定大小,创建的好处了以避免HashMap的频繁的扩容导致性能的损耗
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("go", "123");
        map.put("python", "123");
        map.put("java", "123");
        map.put("c++", "123");
    }

    @Before
    public void before_list_new_arraylist() {
        list = new ArrayList<>();
        list.add(null);
        list.add("陕西省");
        list.add(123456);
        list.add(true);
    }

    @Test
    public void string_joiner_java_test() {
        //StringJoiner一些情况下可以代替StringBuilder
        StringJoiner sj = new StringJoiner("Hollis");
        sj.add("go");
        sj.add("python");
        System.out.println(sj.toString());

        //拼接的时候加上前后的标志位
        StringJoiner stringJoiner = new StringJoiner(":", "[", "]");
        stringJoiner.add("c++").add("go").add("Java").add("python");
        System.out.println(stringJoiner.toString());

        List<String> list = ImmutableList.of("c++", "go", "Java");
        //list拼接字符串,java方法
        String collect = String.join(",", list);
        System.out.println(collect);
        //list拼接字符串,guava方法
        String join = Joiner.on(",").join(list);
        System.out.println(join);
    }

    @Test
    public void test_list_guava_joiner() {

        //忽略Null
        String join = Joiner.on(",").skipNulls().join(list);
        System.out.println(join);
        //用其他值代替null
        String join1 = Joiner.on(",").useForNull("未填写").join(list);
        System.out.println(join1);

        //字符串拼接成list,并忽略空字符串
        String str = "java,list , python, dasdgo";
        List<String> stringList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(str);

        List<String> collect = stringList.stream().map(e -> e.substring(e.length() - 2)).collect(Collectors.toList());

        System.out.println(collect);
    }

    @Test
    public void test_map_guava_joiner() {

        //拼接map
        String join = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(join);

        //分隔字符串生成map
        String str = "id=123&name=green&age=10";
        Map<String, String> join1 = Splitter.on("&").withKeyValueSeparator("=").split(str);
        System.out.println(join1.toString());

    }

    /**
     * 创建不可变对象
     * <p>
     * 1.在多线程操作下，是线程安全的。
     * <p>
     * 2.所有不可变集合会比可变集合更有效的利用资源。
     * <p>
     * 3.中途不可改变
     */
    @Test
    public void test_java_guava_immutable() {
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");

        //下面的方法已经禁用,运行会报错
        //iList.remove(2);
        //iList.add("a");
        System.out.println(iList);
    }

    @Test
    public void test_java_guava_mulitimap() {

        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        map.put("aa", list);
        System.out.println(map.get("aa"));

        Multimap<String, Integer> mapNew = ArrayListMultimap.create();
        mapNew.put("aa", 1);
        mapNew.put("aa", 2);
        System.out.println(mapNew.get("aa"));

        Map<String, Collection<Integer>> stringCollectionMap = mapNew.asMap();
        System.out.println(stringCollectionMap.get("aa"));
    }

    /**
     * map转换成格式化string输出
     */
    @Test
    public void test_java_guava_map_switch_string() {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("xiaoming", 12);
        map.put("xiaohong", 13);
        String result = Joiner.on(",").withKeyValueSeparator("--").join(map);
        System.out.println(result);

        String resultKey = Joiner.on(",").join(map.keySet());
        System.out.println(resultKey);

        String resultValue = Joiner.on(",").join(map.values());
        System.out.println(resultValue);
    }

    /**
     * string格式化为list输出
     */
    @Test
    public void test_java_guava_string_switch_list() {
        String str = "1-2-3-4- 5- 6 ";
        List<String> list = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        System.out.println(list);
    }


    @Test
    public void test_java_string_switch_list() {
        String str = "";
        System.out.println(convertListToString(str));
    }


    public String convertListToString(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String[] strlist = str.trim().split(",");
        StringBuilder sb = new StringBuilder();
        if (strlist.length > 0) {
            for (int i = 0; i < strlist.length; i++) {
                if (i == 0) {
                    sb.append("'").append(strlist[i]).append("'");
                } else {
                    sb.append(",").append("'").append(strlist[i]).append("'");
                }
            }
        }
        return sb.toString();

    }


    @Test
    public void test_binarySearch() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        int idx = Collections.binarySearch(list, "6");
        System.out.println("二分查找：" + idx);
    }


    private List<String> converStringToList(String strs) {
        if (StringUtils.isNotBlank(strs)) {
            String[] idStrs = strs.trim().split(",");
            if (idStrs.length > 0) {
                List<String> sirsList = new ArrayList<>();
                for (String str : idStrs) {
                    if (StringUtils.isNotBlank(str)) {
                        sirsList.add(str.trim());
                    }
                }
                if (sirsList.size() > 0) {
                    return sirsList;
                }
            }
        }
        return null;
    }
}
