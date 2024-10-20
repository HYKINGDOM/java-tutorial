package com.java.tutorial.project.processor;

import org.junit.jupiter.api.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;

/**
 * @author meta
 */
public class GithubRepoPageProcessorTest {

    @Test
    public void test_demo_01() {
        Spider.create(new GithubRepoPageProcessor())
            //从开始抓
            .addUrl("https://github.com/johnlui/DIYSearchEngine")
            //保存文件
            .addPipeline(new ConsolePipeline()).addPipeline(new FilePipeline("D:\\webmagic\\"))
            //开启5个线程抓取
            .thread(5)
            //启动爬虫
            .run();
    }

}