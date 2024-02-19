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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark                                    (size)  Mode  Cnt           Score           Error  Units
 * ParallelBenchmark.forEachByForEach               10  avgt   40      181751.134 ±      6163.860  ns/op
 * ParallelBenchmark.forEachByForEach              100  avgt   40      186097.281 ±      3743.425  ns/op
 * ParallelBenchmark.forEachByForEach             1000  avgt   40      423517.110 ±     32558.178  ns/op
 * ParallelBenchmark.forEachByForEach            10000  avgt   40     1973497.036 ±    356187.042  ns/op
 * ParallelBenchmark.forEachByForEach           100000  avgt   40    16715234.793 ±   4575919.276  ns/op
 * ParallelBenchmark.forEachByForEach          1000000  avgt   40   138115494.834 ±  10996101.975  ns/op
 * ParallelBenchmark.forEachByForEach         10000000  avgt   40  1377060266.250 ± 146249991.341  ns/op
 * ParallelBenchmark.forEachByForEach         20000000  avgt   40  2286827695.000 ± 198556562.770  ns/op
 * ParallelBenchmark.forEachByIterator              10  avgt   40      196323.091 ±      5893.516  ns/op
 * ParallelBenchmark.forEachByIterator             100  avgt   40      202986.830 ±      5879.282  ns/op
 * ParallelBenchmark.forEachByIterator            1000  avgt   40      555109.536 ±    188109.509  ns/op
 * ParallelBenchmark.forEachByIterator           10000  avgt   40     1833002.810 ±     98632.713  ns/op
 * ParallelBenchmark.forEachByIterator          100000  avgt   40    15547876.599 ±    633581.129  ns/op
 * ParallelBenchmark.forEachByIterator         1000000  avgt   40   148910958.645 ±   7853849.838  ns/op
 * ParallelBenchmark.forEachByIterator        10000000  avgt   40  1280810293.750 ± 125384995.529  ns/op
 * ParallelBenchmark.forEachByIterator        20000000  avgt   40  2472899012.500 ± 211595210.346  ns/op
 * ParallelBenchmark.forEachByParallelStream        10  avgt   40      210942.418 ±      6708.539  ns/op
 * ParallelBenchmark.forEachByParallelStream       100  avgt   40      352691.616 ±     14769.540  ns/op
 * ParallelBenchmark.forEachByParallelStream      1000  avgt   40      766741.734 ±    110289.703  ns/op
 * ParallelBenchmark.forEachByParallelStream     10000  avgt   40     3321753.360 ±    137092.157  ns/op
 * ParallelBenchmark.forEachByParallelStream    100000  avgt   40    28197050.596 ±   1028842.027  ns/op
 * ParallelBenchmark.forEachByParallelStream   1000000  avgt   40   289048350.375 ±   8012599.914  ns/op
 * ParallelBenchmark.forEachByParallelStream  10000000  avgt   40  2483604050.000 ± 187148008.616  ns/op
 * ParallelBenchmark.forEachByParallelStream  20000000  avgt   40  4343389865.000 ± 131164198.888  ns/op
 */
@Warmup(iterations = 5, time = 1) // 先预热5轮
@Measurement(iterations = 5, time = 1)// 进行5轮测试
@Fork(value = 8, jvmArgsAppend = {"-XX:+UseG1GC", "-Xms8g", "-Xmx8g"})// Fork进行的数目
@BenchmarkMode(Mode.AverageTime)// 平均时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)// 结果所使用的时间单位
@State(Scope.Benchmark) // 每个Benchmark分配一个实例
public class ParallelIOBenchmark {

    private final RandomDataUtils randomDataUtils = new RandomDataUtils();
    @Param({"10", "100", "1000", "10000", "100000", "1000000", "10000000", "20000000"})
    int size;
    private List<String> list;

    @Setup(Level.Trial)
    public void setup() {
        list = new ArrayList<>(size);
        list.addAll(randomDataUtils.generateRandomChineseStrings(size, 8));
    }

    @Benchmark
    public void forEachByIterator(Blackhole blackhole) throws IOException {
        FileWriter writer = new FileWriter(getFileName("forEachByIterator"));
        for (String value : list) {
            writer.write(value);
            blackhole.consume(value);
        }
    }

    @Benchmark
    public void forEachByForEach(Blackhole blackhole) throws IOException {
        FileWriter writer = new FileWriter(getFileName("forEachByForEach"));
        list.forEach(str -> {
            try {
                writer.write(str);
                blackhole.consume(str);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Benchmark
    public void forEachByParallelStream(Blackhole blackhole) throws IOException {
        FileWriter writer = new FileWriter(getFileName("forEachByParallelStream"));
        list.parallelStream().forEach(str -> {
            try {
                writer.write(str);
                blackhole.consume(str);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String getFileName(String namespace) {
        String filePath = "D:\\list\\param" + File.pathSeparator + namespace;
        return filePath + File.pathSeparator + size + ".txt";
    }
}