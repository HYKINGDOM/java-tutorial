package com.java.tutorial.project.http;

import static com.java.tutorial.project.http.FileDownload.saveVideoFile;
import static com.java.tutorial.project.http.FileDownload.saveVideoFileProgress;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;

import org.assertj.core.util.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.hutool.core.util.StrUtil;

public class JsoupTest {

    private HttpClientUtils HTTP_CLIENT_UTIL;

    @BeforeEach
    public void init_data() {
        HTTP_CLIENT_UTIL = new HttpClientUtils();
    }

    @Test
    public void test_jsoup_demo_01() throws IOException {
        Document doc = Jsoup.parse(new File("e:\\register.html"), "utf-8");
        Element loginform = doc.getElementById("registerform");

        Elements inputElements = loginform.getElementsByTag("input");
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");
            System.out.println("Param name: " + key + " \nParam value: " + value);
        }

        String url = "https://mp.weixin.qq.com/s/U4ooFnNyLrFDjSil8UxsBA";
    }

    @Test
    public void test_jsoup_demo_02() throws IOException {
        String url = "https://www.javsee.work/forum/forum.php?mod=viewthread&tid=120330&extra=page%3D1";

        String sendHttpAsync = HTTP_CLIENT_UTIL.sendHttpAsync(url);

        Document document = Jsoup.parseBodyFragment(sendHttpAsync);

        Elements imgElements = document.select("img");

        List<String> imgUrls = Lists.newArrayList();

        for (Element element : imgElements) {
            String imgUrl = element.attr("src");
            if (StrUtil.isNotEmpty(imgUrl) && imgUrl.startsWith("https")) {
                imgUrls.add(imgUrl);
                System.out.println("imgUrls: " + imgUrl);
                FileDownload.wdownloadImage(imgUrl);
            }
        }

    }

    @Test
    public void test_jsoup_demo_03() throws IOException {
        String videoUrl = "https://example.com/video.mp4";
        String savePath = "/path/to/save/video.mp4";

        HttpResponse<InputStream> response = HTTP_CLIENT_UTIL.downLoadVideo(videoUrl);
        saveVideoFile(savePath, response);
    }

    @Test
    public void test_jsoup_demo_04() throws IOException {

        String videoUrl = "https://example.com/video.mp4";
        String savePath = "/path/to/save/video.mp4";

        HttpResponse<InputStream> response = HTTP_CLIENT_UTIL.downLoadVideo(videoUrl);
        saveVideoFileProgress(savePath, response);
    }
}
