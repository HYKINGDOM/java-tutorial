package com.java.coco.scs.javatest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.java.coco.scs.domain.Person;
import com.java.coco.scs.domain.TimeMap;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yihur
 * @description jdk 1.8 的lamada表达式的使用范例
 * @date 2019/4/23
 */
public class TestGuavaStream {


    private List<Map<String, Object>> lists;

    private List<Map<String, Object>> listCopy;

    private Map<String, Object> map;


    private List<TimeMap> timeMapList;

    private List<Person> persons;

    /**
     * 初始化数据
     */
    @Before
    public void before_map_new_hashMap() {
        //用guava的方法创建HashMap,这样初始化的时候需要制定HashMap的大小,一般大小指定为:(长度/0.75)+1
        //这样指定大小,创建的好处了以避免HashMap的频繁的扩容导致性能的损耗
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("go", "wqeqwe");
        map.put("python", "eqwe");
        map.put("java", "12sdf3");
        map.put("c++", "12fsdf3");
        map.put("JavaScript", "78797987");
        map.put("ruby", "4564");
        map.put("php", "4654654");
        map.put("c", "465465");
    }

    /**
     * 初始化数据
     */
    @Before
    public void before_java_junit() {
        lists = Lists.newArrayListWithCapacity(8);
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "theyihur");
        map.put("age", "18");
        map.put("nation", "china");
        map.put("type", "1");
        lists.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "thezys");
        map.put("age", "21");
        map.put("nation", "amirica");
        map.put("type", "2");
        lists.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "thedgd");
        map.put("age", "22");
        map.put("nation", "jipan");
        map.put("type", "3");
        lists.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "tom");
        map.put("age", "23");
        map.put("nation", "louye");
        map.put("type", "4");
        lists.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "jack");
        map.put("age", "22");
        map.put("nation", "indian");
        map.put("type", "1");
        lists.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "fice");
        map.put("age", "12");
        map.put("nation", "malaixiya");
        map.put("type", "3");
        lists.add(map);
    }

    /**
     * 初始化数据
     */
    @Before
    public void before_java_list_copy_junit() {
        listCopy = Lists.newArrayListWithCapacity(8);
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "theyihur");
        map.put("age", "18");
        map.put("nation", "china");
        map.put("type", "1");
        listCopy.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "thezys");
        map.put("age", "123");
        map.put("nation", "amirica");
        map.put("type", "2");
        listCopy.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "thedgd");
        map.put("age", "12");
        map.put("nation", "jipan");
        map.put("type", "3");
        listCopy.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "tom");
        map.put("age", "1231");
        map.put("nation", "louye");
        map.put("type", "4");
        listCopy.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "jack");
        map.put("age", "1231");
        map.put("nation", "indian");
        map.put("type", "1");
        listCopy.add(map);
        map = Maps.newHashMapWithExpectedSize(6);
        map.put("name", "fice");
        map.put("age", "31231");
        map.put("nation", "malaixiya");
        map.put("type", "3");
        listCopy.add(map);
    }

    @Before
    public void before_init_java_local_time_compete() {
        LocalDateTime localDateTime0 = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime0.plusDays(1);
        LocalDateTime localDateTime2 = localDateTime0.plusDays(5);
        LocalDateTime localDateTime3 = localDateTime0.plusDays(7);
        LocalDateTime localDateTime4 = localDateTime0.plusDays(4);

        timeMapList = Lists.newArrayList(
                new TimeMap(0, localDateTime0, 5),
                new TimeMap(1, localDateTime1, 3),
                new TimeMap(2, localDateTime2, null),
                new TimeMap(3, null, 3),
                new TimeMap(4, localDateTime4, 5));


        persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

    }


    /**
     * 基于Lamada表达式的list和map的遍历
     */
    @Test
    public void test_java_stream_foreach() {


        String sortName = "name";

        boolean desc = Boolean.FALSE;

        List<Person> personList = persons.stream().map(getPersonMapFunction()).sorted(getMapComparator(sortName, desc)).map(getMapPersonFunction()).collect(Collectors.toList());

        personList.forEach(System.out::println);
    }

    private static Comparator<Map<String, Object>> getMapComparator(String sortName, boolean desc) {
        return (o1, o2) -> strCompare(Optional.ofNullable(o1.get(sortName)).map(String::valueOf).orElse(""), Optional.ofNullable(o2.get(sortName)).map(String::valueOf).orElse(""), desc);
    }

    private static Function<Map<String, Object>, Person> getMapPersonFunction() {
        return map1 -> BeanUtil.toBean(map1, Person.class);
    }

    private static Function<Person, Map<String, Object>> getPersonMapFunction() {
        return person -> BeanUtil.beanToMap(person);
    }

    public static int strCompare(CharSequence str1, CharSequence str2, boolean nullIsLess) {
        if (str1 == str2) {
            return 0;
        } else if (str1 == null) {
            return nullIsLess ? -1 : 1;
        } else if (str2 == null) {
            return nullIsLess ? 1 : -1;
        } else {
            return str1.toString().compareTo(str2.toString());
        }
    }


}
