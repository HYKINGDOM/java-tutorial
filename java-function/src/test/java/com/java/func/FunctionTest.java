package com.java.func;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.java.coco.utils.RanDomCollectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
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
        Supplier<List<String>> dataSupplier = () -> RanDomCollectUtils.randomCollectList(13, 100);


        Predicate<String> dataPredicate = e -> e.contains("Z");


        Consumer<String> dataConsumer = e -> {
            e = e.toUpperCase(Locale.ROOT);
            System.out.println(e);
        };


        Function<String,String> stringFunction = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };



        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return StringUtils.compareIgnoreCase(o1.substring(0, 1), o2.substring(0, 1));
            }
        };


        List<String> stringList = dataSupplier.get();

        assert stringList != null;
        List<String> stringList1 = stringList.stream()
                .filter(dataPredicate)
//                .peek(dataConsumer)
                .map(stringFunction)
                .sorted(stringComparator)
//                .peek(dataConsumer)
                .collect(Collectors.toList());


        System.out.println(stringList1.size());
        for (String s : stringList1) {
            System.out.println(s);
        }

    }

}
