package com.java.util.javautil.collection;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1) // 先预热5轮
@Measurement(iterations = 5, time = 1)// 进行5轮测试
@Fork(value = 2, jvmArgsAppend = {"-XX:+UseG1GC", "-Xms8g", "-Xmx8g"})// Fork进行的数目
@BenchmarkMode(Mode.AverageTime)// 平均时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)// 结果所使用的时间单位
@State(Scope.Benchmark) // 每个Benchmark分配一个实例
public class ListBenchmark {

    @Param({"10", "100", "200", "500", "1000", "10000", "50000", "75000", "100000", "1000000", "10000000"})
    int size;

    @Param({"Long", "Integer", "String"})
    String dataType;

    List<Object> list = new ArrayList<>();

    @Setup
    public void setup() {

        RandomDataUtils randomDataUtils = new RandomDataUtils();

        switch (dataType) {
            case "Long" -> list.addAll(randomDataUtils.generateRandomLongs(size));
            case "Integer" -> list.addAll(randomDataUtils.generateRandomInts(size));
            case "String" -> list.addAll(randomDataUtils.generateRandomChineseStrings(size, 8));
            default -> throw new AssertionError();
        }

        System.out.printf("==============数据准备完成====size: %s,  dataType:%s==============", size,  dataType);
    }

    @Benchmark
    public void forEachByIterator(Blackhole blackhole) {
        for (Object value : list) {
            blackhole.consume(value);
        }
    }

    @Benchmark
    public void forEachByMethod(Blackhole blackhole) {
        list.forEach(blackhole::consume);
    }

    @Benchmark
    public void forEachByStream(Blackhole blackhole) {
        list.parallelStream().forEach(blackhole::consume);
    }
}