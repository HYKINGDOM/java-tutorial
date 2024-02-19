package com.java.tutorial.project.processor;

import org.junit.jupiter.api.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;

import static org.junit.jupiter.api.Assertions.*;

public class DemoProcessorTest {

    @Test
    public void test_demo_processor_01() {
        Spider.create(new DemoProcessor())
            //从开始抓
            .addUrl("https://juejin.cn/post/7264780458738434089")
            //保存文件
            .addPipeline(new ConsolePipeline())
            //开启5个线程抓取
            .thread(5)
            //启动爬虫
            .run();
    }
}