package com.java.coco.utils;

import cn.hutool.core.util.ObjectUtil;

import java.util.Iterator;
import java.util.Map;

/**
 * map 工具类
 *
 * @author hy
 */
public class MapUtil {

    /**
     * 去除Map中值为{@code null} or Empty 的键值对<br> 注意：此方法在传入的Map上直接修改。
     *
     * @param <K> key的类型
     * @param <V> value的类型
     * @param map Map
     */
    public static <K, V> void removeNullAndEmptyValue(Map<K, V> map) {
        if (ObjectUtil.isEmpty(map)) {
            return;
        }

        final Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
        Map.Entry<K, V> entry;
        while (iter.hasNext()) {
            entry = iter.next();
            if (null == entry.getValue() || ObjectUtil.isEmpty(entry.getValue())) {
                iter.remove();
            }
        }
    }
}
