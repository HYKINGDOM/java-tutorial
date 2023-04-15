package com.java.tutorial.project;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gui.ava.html.Html2Image;
import gui.ava.html.renderer.ImageRenderer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

/**
 * Html转换图片
 *
 * @author ：哈哈
 * @date ：2022-09-01
 */
@Slf4j
@Component
public class HtmlConvertImgHelper {

    @Resource
    private Configuration configuration;


    /**
     * freemarker转Image
     *
     * @param fileName   ftl文件名称，需要在resources/templates目录下
     * @param formatType
     * @return
     * @throws IOException
     */
    public byte[] htmlConvertImg(String fileName, Object map, String formatType) throws IOException, TemplateException {
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(fileName), map);
        return htmlConvertImg(htmlText, formatType);
    }

    /**
     * 根据HTML内容转Image
     *
     * @param htmText    HTML文本字符串
     * @param formatType 图片类型
     */
    public byte[] htmlConvertImg(String htmText, String formatType) throws IOException {
        //最终返回的byte流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Html2Image html2Image = Html2Image.fromHtml(htmText);
        ImageRenderer imageRenderer = html2Image.getImageRenderer();
        BufferedImage grayPicture = imageRenderer.getBufferedImage(BufferedImage.TYPE_INT_RGB);
        ImageIO.write(grayPicture, formatType, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }




    public InputStream getHtmlInputStreams(Map<String, Object> root, String template){

        Template temp = null;
        StringWriter out = new StringWriter();
        try {
            temp = configuration.getTemplate(template + ".ftl");
            temp.process(root, out);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        StringBuffer buffer = out.getBuffer();
        return new ByteArrayInputStream(new String(buffer).getBytes());
    }

}
