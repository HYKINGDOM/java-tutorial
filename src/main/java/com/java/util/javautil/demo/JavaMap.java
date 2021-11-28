package com.java.util.javautil.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class JavaMap {


    private static HashMap<Integer, String> stringHashMap = new HashMap<>();

    public static void main(String[] args) {
        stringHashMap.put(1, "one");
        stringHashMap.put(2, "two");
        stringHashMap.put(3, "three");

        JavaMap javaMap = new JavaMap();
        javaMap.mapCompute();


        for (Map.Entry<Integer, String> stringEntry : stringHashMap.entrySet()) {


        }


        for (Integer integer : stringHashMap.keySet()) {

        }


        for (Map.Entry<Integer, String> integerStringEntry : stringHashMap.entrySet()) {
            System.out.println(integerStringEntry.toString());
        }
    }

    /**
     * 查询Map中指定的值，不存在时使用默认值
     */
    public void mapGetOrDefault() {
        if (stringHashMap.containsKey(4)) {
            System.out.println(stringHashMap.get(4));
        } else {
            System.out.println("NoValue");
        }
        // Java8使用Map.getOrDefault()
        System.out.println(stringHashMap.getOrDefault(4, "NoValue"));
    }

    /**
     * 当key不存在时将数据add到map 并返回null
     * 当key存在时不修改原值,并返回该key对应的value
     */
    public void mapPutIfAbsent() {
        String www = stringHashMap.putIfAbsent(3, "www");
        System.out.println(www);

    }

    /**
     * 根据key删除value
     */
    public void mapRemove() {
        stringHashMap.remove(1);
    }

    /**
     * map的单个替换
     * 第一种会将key对应的value替换
     * 第二种首先会匹配第二个参数oldvalue 如没匹配上则不替换,
     */
    public void mapReplace() {
        stringHashMap.replace(1, "qqqq");
        stringHashMap.replace(2, "two", "dasdas");
    }

    /**
     *
     */
    public void mapReplaceAll() {
        stringHashMap.replaceAll((integer, s) -> s.toUpperCase());

        stringHashMap.replaceAll(new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) {
                return s.toUpperCase();
            }
        });
    }


    /**
     * 如果 Map 中 key 对应的映射不存在或者为 null，则将 value（不能是 null）关联到 key 上；
     *
     *
     * 否则执行 Function，如果执行结果非 null 则用该结果跟 key 关联，否则在 Map 中删除 key 的映射．
     *
     */
    public void mapMerge() {
        stringHashMap.merge(2, "测试字段", new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return null;
            }
        });

    }


    /**
     * 用是把 remappingFunction 的计算结果关联到 key 上，如果计算结果为 null，则在 Map 中删除 key 的映射．
     */
    public void mapCompute(){
        stringHashMap.compute(2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) {
                return s.concat("eaweqweqe");
            }
        });
        String newMsg ="dasda";
        stringHashMap.compute(3, (k,v) -> v==null ? newMsg : v.concat(newMsg));
    }


    public void mapComputeIfAbsent(){
        stringHashMap.computeIfAbsent(2, new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return null;
            }
        });
    }


    public void mapComputeIfPresent(){

    }

}
