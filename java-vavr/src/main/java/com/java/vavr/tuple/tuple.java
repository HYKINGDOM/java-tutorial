package com.java.vavr.tuple;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;

public class tuple {

    public static void main(String[] args) {
        //可以存放不同类型的对象并维持其类型信息，这样在取值时就不用 cast 了
        Tuple2<String, Integer> tuple2 = Tuple.of("Java", 8);
        String str = tuple2._1();
        Integer integer2 = tuple2._2();
        //当前上限为8
        Tuple3<String, Integer, Long> tuple3 = Tuple.of("Java", 8, 100L);
        String string = tuple3._1();
        Integer integer3 = tuple3._2();
        Long aLong = tuple3._3();

        //映射计算元组中每个元素的函数，返回另一个元组
        Tuple2<String, Integer> thatMap1 = tuple2.map(s -> s.substring(2) + "vr", i -> i / 8);

        Tuple2<String, Integer> thatMap2 = tuple2.map((s, i) -> Tuple.of(s.substring(2) + "vr", i / 8));

        //转换基于元组的内容创建新类型。
        String that = tuple2.apply((s, i) -> s.substring(2) + "vr " + i / 8);

    }
}
