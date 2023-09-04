package com.java.coco.utils;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hy
 */
@Slf4j
public class ObjectConvertUtil {

    public static <T> List<List<Object>> convertObjectListToList(List<T> objectList) {
        List<List<Object>> resultList = new ArrayList<>();
        if (objectList == null || objectList.isEmpty()) {
            return resultList;
        }

        // 获取对象的属性列表
        Class<?> objectClass = objectList.get(0).getClass();
        Field[] fields = objectClass.getDeclaredFields();

        try {
            for (T obj : objectList) {
                List<Object> objectValues = new ArrayList<>();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    objectValues.add(value);
                }
                resultList.add(objectValues);
            }
        } catch (IllegalAccessException e) {
            log.error(ExceptionUtil.getRootCauseMessage(e));
        }

        return resultList;
    }

    public static <T> List<List<Object>> convertObjectListToListV2(List<T> objectList) {
        if (objectList == null || objectList.isEmpty()) {
            return Collections.emptyList();
        }

        Class<?> objectClass = objectList.get(0).getClass();
        Field[] fields = objectClass.getDeclaredFields();

        return objectList.stream().map(obj -> {
            try {
                List<Object> values = new ArrayList<>();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    values.add(value);
                }
                return values;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
