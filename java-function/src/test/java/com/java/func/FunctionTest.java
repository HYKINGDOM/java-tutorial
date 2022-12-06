package com.java.func;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.java.coco.utils.RanDomCollectUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionTest {


    @Test
    public void test_Function() {
        Supplier<List<String>> dataSupplier = () -> RanDomCollectUtils.randomCollectList(13,100);


        Predicate<String> dataPredicate = e -> e.contains("Z");


        Consumer<String> dataConsumer = s -> s = s.toLowerCase();


        List<String> stringList = dataSupplier.get();

        assert stringList != null;
        List<String> stringList1 = stringList.stream().filter(dataPredicate).peek(dataConsumer).collect(Collectors.toList());


        System.out.println(stringList1.size());
        for (String s : stringList1) {
            System.out.println(s);
        }

    }

}
