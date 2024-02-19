package com.java.benchmark;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 1, time = 1) // 先预热5轮
@Measurement(iterations = 3, time = 1)// 进行5轮测试
@Fork(value = 2, jvmArgsAppend = {"-XX:+UseG1GC", "-Xms8g", "-Xmx8g"})// Fork进行的数目
@BenchmarkMode(Mode.AverageTime)// 平均时间
@OutputTimeUnit(TimeUnit.SECONDS)// 结果所使用的时间单位
@State(Scope.Benchmark) // 每个Benchmark分配一个实例
public class ContainsBenchmark {

    private final RandomDataUtils randomDataUtils = new RandomDataUtils();
    @Param({"10", "100", "200", "500", "1000", "10000", "50000", "75000", "100000", "1000000"})
    int size;
    @Param({"ArrayList"})
    String type;
    @Param({"String"})
    String dataType;
    private List<String> list;
    private List<String> blist;

    @Setup(Level.Trial)
    public void setup() {

        if (type.equals("ArrayList")) {
            list = new ArrayList<>(size);
            blist = new ArrayList<>(size);
        } else {
            throw new AssertionError();
        }

        if (dataType.equals("String")) {
            for (int i = 0; i < size; i++) {
                String randomString = RandomUtil.randomString(13);
                list.add(randomString);

                if (i < size / 2) {
                    blist.add(randomString);
                } else {
                    blist.add(RandomUtil.randomString(13));
                }
            }
        } else {
            throw new AssertionError();
        }

    }

    @Benchmark
    public void listRemoveIf(Blackhole blackhole) {

        list.removeIf(e -> {

            boolean contains = blist.contains(e);
            blackhole.consume(contains);
            return contains;

        });
    }

    @Benchmark
    public void listRemoveAll(Blackhole blackhole) {
        blackhole.consume(list.removeAll(blist));
    }

    @Benchmark
    public void iteratorRemove(Blackhole blackhole) {
        Set<String> set = new HashSet<>(blist);
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if (set.contains(next)) {
                blackhole.consume(next);
                iterator.remove();
            }
        }
    }

    @TearDown(Level.Trial)
    public void test() {
    }

    @Test
    public void test_demo_01() throws RunnerException {
        Options opt = new OptionsBuilder().include(ContainsBenchmark.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}