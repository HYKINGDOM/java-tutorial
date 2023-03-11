package com.java.coco.scs.javatest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.util.StrUtil;
import com.java.coco.scs.domain.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 自定义排序
 *
 * @author yihur
 */
public class TestSortedStream {

    private List<Person> persons;

    @Test
    public void test_java_stream_sorted_01() {

        persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));


        String sortName = "name";

        boolean desc = Boolean.FALSE;


        sortPersonListByFiled(sortName, desc).forEach(System.out::println);
    }


    @Test
    public void test_java_stream_sorted_02() {

        persons =
                Arrays.asList(
                        new Person(null, 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));


        String sortName = "name";

        boolean desc = Boolean.FALSE;

        sortPersonListByFiled(sortName, desc).forEach(System.out::println);
    }


    @Test
    public void test_java_stream_sorted_03() {

        persons =
                Arrays.asList(
                        new Person(null, 13),
                        new Person("Peter", null),
                        new Person("Pamela", 34),
                        new Person("David", 12));


        String sortName = "age";

        boolean desc = Boolean.FALSE;

        sortPersonListByFiled(sortName, desc).forEach(System.out::println);
    }

    @Test
    public void test_java_stream_sorted_04() {

        persons =
                Arrays.asList(
                        new Person(null, 13),
                        new Person("Peter", null),
                        new Person("Pamela", 34),
                        new Person("David", 12));


        String sortName = "age";

        boolean desc = Boolean.TRUE;

        sortPersonListByFiled(sortName, desc).forEach(System.out::println);
    }

    private List<Person> sortPersonListByFiled(String sortName, boolean desc) {
        return persons.stream()
                .map(getPersonMapFunction())
                .sorted(getMapComparator(sortName, desc))
                .map(getMapPersonFunction())
                .collect(Collectors.toList());
    }

    private static Comparator<Map<String, Object>> getMapComparator(String sortName, boolean desc) {
        return (o1, o2) -> strCompare(Optional.ofNullable(o1.get(sortName)).map(String::valueOf).orElse(null), Optional.ofNullable(o2.get(sortName)).map(String::valueOf).orElse(null), desc);
    }

    private static Comparator<Map<String, Object>> getMapStrUtilComparator(String sortName, boolean desc) {
        return (o1, o2) -> StrUtil.compare(Optional.ofNullable(o1.get(sortName)).map(String::valueOf).orElse(null), Optional.ofNullable(o2.get(sortName)).map(String::valueOf).orElse(null), desc);
    }

    private static Comparator<Map<String, Object>> getMapComparatorHasNull(String sortName, boolean desc) {
        return (o1, o2) -> CompareUtil.compare(o1.get(sortName), o2.get(sortName), desc);
    }

    private static Function<Map<String, Object>, Person> getMapPersonFunction() {
        return map1 -> BeanUtil.toBean(map1, Person.class);
    }

    private static Function<Person, Map<String, Object>> getPersonMapFunction() {
        return BeanUtil::beanToMap;
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
