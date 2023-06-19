package com.java.tutorial.project.http;

import cn.hutool.core.util.StrUtil;
import org.assertj.core.util.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

public class JsoupTest {


    @Test
    public void test_jsoup_demo_01() throws IOException {
        Document doc = Jsoup.parse(new File("e:\\register.html"),"utf-8");
        Element loginform = doc.getElementById("registerform");

        Elements inputElements = loginform.getElementsByTag("input");
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");
            System.out.println("Param name: "+key+" \nParam value: "+value);
        }


        String url = "https://mp.weixin.qq.com/s/U4ooFnNyLrFDjSil8UxsBA";
    }


    @Test
    public void test_jsoup_demo_02() throws IOException {
        String url = "https://mp.weixin.qq.com/s/U4ooFnNyLrFDjSil8UxsBA";

        HttpClientUtils httpClientUtils = new HttpClientUtils();

        String sendHttpAsync = httpClientUtils.sendHttpAsync(url);

        Document document = Jsoup.parseBodyFragment(sendHttpAsync);


        Elements imgElements = document.select("img");

        List<String> imgUrls = Lists.newArrayList();

        for (Element element : imgElements) {
            String imgUrl = element.attr("data-src");
            if (StrUtil.isNotEmpty(imgUrl)){
                imgUrls.add(imgUrl);
                System.out.println("imgUrls: "+ imgUrl);
                downloadImage(imgUrl);
            }
        }

    }



    private static void downloadImage(String imgUrl) throws IOException {
        URL url = new URL(imgUrl);

        File file = new File("d:" + File.separator + Calendar.getInstance().getTimeInMillis() + ".jpeg");

        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
