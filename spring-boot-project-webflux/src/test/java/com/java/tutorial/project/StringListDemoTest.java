package com.java.tutorial.project;

import cn.hutool.core.util.RandomUtil;
import com.google.common.hash.BloomFilter;

import com.github.mgunlogson.cuckoofilter4j.CuckooFilter;
import com.google.common.hash.Funnels;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.RamUsageEstimator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StringListDemoTest {

    private static List<String> aDemo;

    private static List<String> bDemo;

    @BeforeEach
    public void ini_data() {

        aDemo = new ArrayList<>(100000);
        bDemo = new ArrayList<>(100000);

        for (int i = 0; i < 100000; i++) {
            String randomString = RandomUtil.randomString(13);
            aDemo.add(randomString);

            if (i < 60000) {
                bDemo.add(randomString);
            } else {
                bDemo.add(RandomUtil.randomString(13));
            }
        }
    }

    @Test
    public void test_demo_01() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        aDemo.removeIf(e -> bDemo.contains(e));
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds() + " :test_demo_01: " + aDemo.size());
    }

    @Test
    public void test_demo_02() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        aDemo.removeAll(bDemo);
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds() + " :test_demo_02: " + aDemo.size());
    }

    @Test
    public void test_demo_03() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Set<String> set = new HashSet<>(bDemo);
        Iterator<String> iterator = aDemo.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if (set.contains(next)) {
                iterator.remove();
            }
        }
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds() + " :test_demo_03: " + aDemo.size());
    }

    @Test
    public void test_demo_04() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Set<String> set = new HashSet<>(bDemo);

        aDemo.removeIf(set::contains);
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds() + " :test_demo_04: " + aDemo.size());
    }

    @Test
    public void test_demo_05() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        BloomFilter<String> bloomFilter =
            BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), bDemo.size());
        for (String num : bDemo) {
            bloomFilter.put(num);
        }

        aDemo.removeIf(o -> bloomFilter.mightContain(o));
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds() + " :test_demo_05: " + aDemo.size());
    }

    @Test
    public void test_demo_06() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 创建布谷鸟过滤器
        // create
        CuckooFilter<CharSequence> cuckooFilter =
            new CuckooFilter.Builder<>(Funnels.stringFunnel(StandardCharsets.UTF_8), bDemo.size()).build();
        for (String num : bDemo) {
            cuckooFilter.put(num);
        }

        aDemo.removeIf(o -> cuckooFilter.mightContain(o));
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds() + " :test_demo_06: " + aDemo.size());
    }
}
