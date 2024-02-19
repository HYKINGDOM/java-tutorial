package com.java.func;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author HY
 */
public class SeqUtils {

    /**
     * 字符串下划线转驼峰- 首字母小写 替换掉下划线 下划线后一个字符大写
     *
     * @param str
     * @return
     */
    public static String underscoreToCamel(String str) {
        // Java没有首字母变小写方法，随便现写一个
        UnaryOperator<String> capitalize = s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        // 利用生成器构造一个方法的流
        Seq<UnaryOperator<String>> seq = consumer -> {
            // yield第一个小写函数
            consumer.accept(String::toLowerCase);
            // 这里IDEA会告警，提示死循环风险，无视即可
            while (true) {
                // 按需yield首字母大写函数
                consumer.accept(capitalize);
            }
        };
        List<String> split = Arrays.asList(str.split("_"));
        // 这里的zip和join都在上文给出了实现
        return seq.zip(split, Function::apply).join("");
    }
}
