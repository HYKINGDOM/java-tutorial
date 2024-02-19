package com.java.coco.system.manager;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yihur
 * @description list 交集并集差集
 * @date 2019/4/9
 */
public class GuavaList {

    public static void main(String[] args) {
        Set<Integer> sets = Sets.newHashSet(1, 2, 3, 4, 5, 6);
        Set<Integer> sets2 = Sets.newHashSet(3, 4, 5, 6, 7, 8, 9);
        // 交集
        System.out.println("交集为：");
        Sets.SetView<Integer> intersection = Sets.intersection(sets, sets2);
        System.out.println(Arrays.toString(intersection.toArray()));
        // 差集
        System.out.println("差集为：");
        Sets.SetView<Integer> diff = Sets.difference(sets, intersection);
        System.out.println(Arrays.toString(diff.toArray()));
        Sets.SetView<Integer> diff2 = Sets.difference(sets2, intersection);
        System.out.println(Arrays.toString(diff2.toArray()));
        // 并集
        System.out.println("并集为：");
        Sets.SetView<Integer> union = Sets.union(sets, sets2);
        System.out.println(Arrays.toString(union.toArray()));
    }

    @Test
    public void test_all_list() {
        Map<String, Object> h1 = new HashMap<>();
        h1.put("ItemIndex", "1");
        h1.put("h123", "jghj");
        h1.put("h124", "jghj");
        h1.put("h125", "ghj");

        Map<String, Object> h2 = new HashMap<>();
        h2.put("ItemIndex", "1");
        h2.put("h123", "feqwdsa");
        h2.put("h124", "fdeqwesa");
        h2.put("h125", "fdeqwsa");

        Map<String, Object> h3 = new HashMap<>();
        h3.put("ItemIndex", "2");
        h3.put("h123", "fdrwersa");
        h3.put("h124", "fdfdgsa");
        h3.put("h125", "fdwqesa");

        Map<String, Object> h4 = new HashMap<>();
        h4.put("ItemIndex", "2");
        h4.put("h123", "sadsd");
        h4.put("h124", "sada");
        h4.put("h125", "fdasdsa");

        List<Map<String, Object>> lists = new ArrayList<>();
        lists.add(h1);
        lists.add(h2);
        lists.add(h3);
        lists.add(h4);

        System.out.println(h4.toString());

        //        Map<String,Object> map = new HashMap<>();
        //        lists.forEach(x-> x.forEach(map::put));

        //System.out.println(map.toString());

        //lists.stream().map(e -> ("1").equals(e.get("ItemIndex"))).collect(Collectors.toList());

        //        Map<String, Object> memberMap = lists.stream().collect(HashMap::new, (m,v)->
        //                m.put(v.get("h312").toString(), Collectors.toList()),HashMap::putAll);
        //

        //        List<String> list = Lists.newArrayList("1", "2", "3", "1");
        //        Map<String, List<String>> mapww = list.stream().collect(Collectors.toMap(key -> key,
        //                value -> Lists.newArrayList(value),
        //                (List<String> newValueList, List<String> oldValueList) -> {
        //                    oldValueList.addAll(newValueList);
        //                    return oldValueList;
        //                }));

        //        Map<Object, List<Map<String, Object>>> mapWall = lists.stream().collect(Collectors.toMap(key -> key.get("ItemIndex"),
        //                Lists::newArrayList,
        //                (List<Map<String,Object>> newValueList, List<Map<String,Object>> oldValueList) -> {
        //                    oldValueList.addAll(newValueList);
        //                    return oldValueList;
        //                }));
        //
        //        System.out.println(mapWall);
        //        lists.stream().collect(Collectors.toMap(k -> k.get("h312"), ));
        //        List<Map<String, Object>> itemIndex1 = stream.filter(e -> ("1").equals(e.get("ItemIndex"))).collect(Collectors.toList());
        //        List<Map<String, Object>> itemIndex2 = stream.filter(e -> ("2").equals(e.get("ItemIndex"))).collect(Collectors.toList());
        //        List<Map<String, Object>> itemIndex3 = stream.filter(e -> ("3").equals(e.get("ItemIndex"))).collect(Collectors.toList());
        //        List<Map<String, Object>> itemIndex4 = stream.filter(e -> ("4").equals(e.get("ItemIndex"))).collect(Collectors.toList());
        //        List<Map<String, Object>> itemIndex5 = stream.filter(e -> ("5").equals(e.get("ItemIndex"))).collect(Collectors.toList());

    }

    @Test
    public void test_stream_list_map() {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> mapStr1 = new HashMap<>();
        mapStr1.put("name", "丽丽");
        mapStr1.put("sex", "女");
        mapStr1.put("age", 22);
        mapStr1.put("tel", "110");
        Map<String, Object> mapStr2 = new HashMap<>();
        mapStr2.put("name", "丽丽");
        mapStr2.put("sex", "女");
        mapStr2.put("age", 23);
        mapStr2.put("tel", "120");
        Map<String, Object> mapStr3 = new HashMap<>();
        mapStr3.put("name", "丽丽");
        mapStr3.put("sex", "女");
        mapStr3.put("age", 24);
        mapStr3.put("tel", "110");
        mapList.add(mapStr1);
        mapList.add(mapStr2);

        mapList.add(mapStr3);

        mapList.forEach(m -> {
            System.out.println("======map========");
            m.keySet().forEach(n -> System.out.println(n + "-->" + m.get(n)));
        });
        //重写比较器
        List<Map<String, Object>> cs = mapList.stream()
            .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>((o1, o2) -> {
                if (o1.get("tel").equals(o2.get("tel"))) {
                    return 0;
                }
                return 1;
            })), ArrayList::new));
        //循环取出结果
        cs.forEach(m -> {
            System.out.println("======map========");
            m.keySet().forEach(n -> System.out.println(n + "-->" + m.get(n)));
        });
    }
}
