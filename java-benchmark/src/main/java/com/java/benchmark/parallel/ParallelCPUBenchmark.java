package com.java.benchmark.parallel;

import com.java.benchmark.RandomDataUtils;
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
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark                                       (size)  Mode  Cnt         Score         Error  Units
 * ParallelCPUBenchmark.forEachByForEach               10  avgt   40         9.682 ±       0.140  ns/op
 * ParallelCPUBenchmark.forEachByForEach              100  avgt   40        71.317 ±       0.914  ns/op
 * ParallelCPUBenchmark.forEachByForEach             1000  avgt   40       838.702 ±       4.184  ns/op
 * ParallelCPUBenchmark.forEachByForEach            10000  avgt   40     38116.741 ±     321.579  ns/op
 * ParallelCPUBenchmark.forEachByForEach           100000  avgt   40    459517.487 ±   14492.821  ns/op
 * ParallelCPUBenchmark.forEachByForEach          1000000  avgt   40   4055440.017 ±  115571.467  ns/op
 * ParallelCPUBenchmark.forEachByForEach         10000000  avgt   40  30178080.558 ±  468116.982  ns/op
 * ParallelCPUBenchmark.forEachByForEach         20000000  avgt   40  65469258.660 ± 3116593.477  ns/op
 * ParallelCPUBenchmark.forEachByIterator              10  avgt   40        19.489 ±       0.244  ns/op
 * ParallelCPUBenchmark.forEachByIterator             100  avgt   40       191.353 ±       2.586  ns/op
 * ParallelCPUBenchmark.forEachByIterator            1000  avgt   40      1966.786 ±      33.740  ns/op
 * ParallelCPUBenchmark.forEachByIterator           10000  avgt   40     44950.202 ±    1015.691  ns/op
 * ParallelCPUBenchmark.forEachByIterator          100000  avgt   40    911581.472 ±   12340.534  ns/op
 * ParallelCPUBenchmark.forEachByIterator         1000000  avgt   40   5273894.122 ±  268699.919  ns/op
 * ParallelCPUBenchmark.forEachByIterator        10000000  avgt   40  38606036.732 ± 1050023.962  ns/op
 * ParallelCPUBenchmark.forEachByIterator        20000000  avgt   40  80406190.433 ± 4631945.420  ns/op
 * ParallelCPUBenchmark.forEachByParallelStream        10  avgt   40     13777.340 ±     291.230  ns/op
 * ParallelCPUBenchmark.forEachByParallelStream       100  avgt   40     27447.633 ±     752.014  ns/op
 * ParallelCPUBenchmark.forEachByParallelStream      1000  avgt   40     23583.564 ±    1515.136  ns/op
 * ParallelCPUBenchmark.forEachByParallelStream     10000  avgt   40     25843.999 ±    1254.856  ns/op
 * ParallelCPUBenchmark.forEachByParallelStream    100000  avgt   40     88061.977 ±   10111.174  ns/op
 * ParallelCPUBenchmark.forEachByParallelStream   1000000  avgt   40   1988671.992 ±   28273.577  ns/op
 * ParallelCPUBenchmark.forEachByParallelStream  10000000  avgt   40  17384590.558 ±  139580.184  ns/op
 * ParallelCPUBenchmark.forEachByParallelStream  20000000  avgt   40  34676600.141 ±  344383.719  ns/op
 */
@Warmup(iterations = 5, time = 1) // 先预热5轮
@Measurement(iterations = 5, time = 1)// 进行5轮测试
@Fork(value = 8, jvmArgsAppend = {"-XX:+UseG1GC", "-Xms8g", "-Xmx8g"})// Fork进行的数目
@BenchmarkMode(Mode.AverageTime)// 平均时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)// 结果所使用的时间单位
@State(Scope.Benchmark) // 每个Benchmark分配一个实例
public class ParallelCPUBenchmark {

    @Param({"10", "100", "1000", "10000", "100000", "1000000", "10000000", "20000000"})
    int size;

    private List<String> list;

    private final RandomDataUtils randomDataUtils = new RandomDataUtils();

    @Setup(Level.Trial)
    public void setup() {
        list = new ArrayList<>(size);
        list.addAll(randomDataUtils.generateRandomChineseStrings(size, 8));
    }

    @Benchmark
    public void forEachByIterator(Blackhole blackhole){
        for (String value : list) {
            blackhole.consume(value);
        }
    }

    @Benchmark
    public void forEachByForEach(Blackhole blackhole){
        list.forEach(blackhole::consume);
    }

    @Benchmark
    public void forEachByParallelStream(Blackhole blackhole) {
        list.parallelStream().forEach(blackhole::consume);
    }

}
