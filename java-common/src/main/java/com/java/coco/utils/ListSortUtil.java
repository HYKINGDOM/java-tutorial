package com.java.coco.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.comparator.CompareUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 自定义排序
 *
 * @author HY
 */
public class ListSortUtil {

    /**
     * 自定义字段排序
     *
     * @param list
     * @param sortName
     * @param tClass
     * @param desc
     * @param descNull
     * @param <T>
     * @return
     */
    public static <T> List<T> sortListByField(List<T> list, String sortName, Class<T> tClass, boolean desc,
        boolean descNull) {

        return list.stream().map(convertBeanToMap()).sorted(getMapComparator(sortName, desc, descNull))
            .map(convertMapToBean(tClass)).collect(Collectors.toList());
    }

    /**
     * 自定义字段排序
     *
     * @param list
     * @param sortName
     * @param desc
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<?> sortListByField(List<T> list, String sortName, boolean desc, Class<T> tClass) {
        return sortListByField(list, sortName, tClass, desc, true);
    }

    /**
     * 是否倒序防范
     *
     * @param sortName
     * @param desc
     * @param descNull
     * @return
     */
    private static Comparator<Map<String, Object>> getMapComparator(String sortName, boolean desc, boolean descNull) {
        if (desc) {
            return (o1, o2) -> getCompareUtil(sortName, descNull, o1, o2);
        }
        return ((Comparator<Map<String, Object>>)(o1, o2) -> getCompareUtil(sortName, descNull, o1, o2)).reversed();
    }

    /**
     * 比较方法
     *
     * @param sortName 排序字段
     * @param descNull null值排序规则
     * @param o1
     * @param o2
     * @return
     */
    private static int getCompareUtil(String sortName, boolean descNull, Map<String, Object> o1,
        Map<String, Object> o2) {
        return CompareUtil.compare(o1.get(sortName), o2.get(sortName), descNull);
    }

    private static <T> Function<Map<String, Object>, T> convertMapToBean(Class<T> tClass) {
        return map -> BeanUtil.toBean(map, tClass);
    }

    private static <T> Function<T, Map<String, Object>> convertBeanToMap() {
        return BeanUtil::beanToMap;
    }

}
