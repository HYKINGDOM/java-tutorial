package com.java.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1) // 先预热5轮
@Measurement(iterations = 5, time = 1)// 进行5轮测试
@Fork(value = 2, jvmArgsAppend = {"-XX:+UseG1GC", "-Xms8g", "-Xmx8g"})// Fork进行的数目
@BenchmarkMode(Mode.AverageTime)// 平均时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)// 结果所使用的时间单位
@State(Scope.Benchmark) // 每个Benchmark分配一个实例
public class ParallelStreamBenchmark {

    @Param({"10", "100", "200", "500", "1000", "10000", "50000", "75000", "100000", "1000000", "5000000", "10000000", "20000000"})
    int size;

    @Param({"ArrayList", "LinkedList"})
    String type;

    @Param({"Long", "Integer", "String"})
    String dataType;

    private List<Object> list;

    private final RandomDataUtils randomDataUtils = new RandomDataUtils();

    @Setup(Level.Trial)
    public void setup() {
        switch (type) {
            case "ArrayList":
                list = new ArrayList<>(size);
                break;
            case "LinkedList":
                list = new LinkedList<>();
                break;
            default:
                throw new AssertionError();
        }

        switch (dataType) {
            case "Long":
                list.addAll(randomDataUtils.generateRandomLongs(size));
                break;
            case "Integer":
                list.addAll(randomDataUtils.generateRandomInts(size));
                break;
            case "String":
                list.addAll(randomDataUtils.generateRandomChineseStrings(size, 8));
                break;
            default:
                throw new AssertionError();
        }

    }

    @Benchmark
    public void forEachByIterator(Blackhole blackhole) {
        for (Object value : list) {
            blackhole.consume(value);
        }
    }

    @Benchmark
    public void forEachByForEach(Blackhole blackhole) {
        list.forEach(blackhole::consume);
    }

    @Benchmark
    public void forEachByParallelStream(Blackhole blackhole) {
        list.parallelStream().forEach(blackhole::consume);
    }

    @TearDown(Level.Trial)
    public void test() {
    }
}