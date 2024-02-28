package com.java.coco.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 按照指定的规则排序
 *
 * @author kscs
 */
public class ComparatorUtils {

    //    public static void main(String[] args) {
    //        List<String> listA = Arrays.asList("2", "3", "4", "5", "6");
    //        List<String> listB = Arrays.asList("3", "4", "9", "5", "1", "2", "6");
    //        customListStrSort(listA, listB);
    //        System.out.println(listA);
    //    }

    public static void customSort(List<Integer> listA, List<Integer> listB) {
        // 使用HashMap来存储ListB中元素的索引
        Map<Integer, Integer> indexMap = new HashMap<>(listB.size());
        for (int i = 0; i < listB.size(); i++) {
            indexMap.put(listB.get(i), i);
        }
        // 自定义比较器
        Comparator<Integer> customComparator = (a, b) -> {
            // 如果两个元素都在ListB中有对应，则根据ListB中的索引进行比较
            if (indexMap.containsKey(a) && indexMap.containsKey(b)) {
                return Integer.compare(indexMap.get(a), indexMap.get(b));
            }
            // 如果a在ListB中有对应，b没有，则a较小
            if (indexMap.containsKey(a)) {
                return -1;
            }
            // 如果b在ListB中有对应，a没有，则b较小
            if (indexMap.containsKey(b)) {
                return 1;
            }
            // 如果都没有对应，则保持原来的顺序（这里可以根据需要调整）
            return 0;
        };
        // 根据自定义的比较器对ListA进行排序
        listA.sort(customComparator);
    }

    public static void customListStrSort(List<String> listA, List<String> listB) {
        // 使用HashMap来存储ListB中元素的索引
        Map<String, Integer> indexMap = new HashMap<>(listB.size());
        for (int i = 0; i < listB.size(); i++) {
            indexMap.put(listB.get(i), i);
        }
        // 自定义比较器
        Comparator<String> customComparator = (a, b) -> {
            // 如果两个元素都在ListB中有对应，则根据ListB中的索引进行比较
            if (indexMap.containsKey(a) && indexMap.containsKey(b)) {
                return Integer.compare(indexMap.get(a), indexMap.get(b));
            }
            // 如果a在ListB中有对应，b没有，则a较小
            if (indexMap.containsKey(a)) {
                return -1;
            }
            // 如果b在ListB中有对应，a没有，则b较小
            if (indexMap.containsKey(b)) {
                return 1;
            }
            // 如果都没有对应，则保持原来的顺序（这里可以根据需要调整）
            return 0;
        };
        // 根据自定义的比较器对ListA进行排序
        listA.sort(customComparator);
    }
}
