package com.java.tutorial.project;

import com.google.common.collect.Lists;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片生成工具
 *
 * @author 哈哈
 * @date 2022/9/1
 */
@Component
public class GenerateImageUtil {

    @Resource
    private HtmlConvertImgHelper htmlConvertImgHelper;

    /**
     * 生成表格
     *
     * @param contentMap 表格内容
     * @param desc       备注
     * @param path       保存路径
     *
     * @throws TemplateException
     * @throws IOException
     */
    public void createSizeTableImage(Map<String, List<String>> contentMap, String desc, String path) {
        Map<String, List<String>> descMap = new HashMap<>();
        List<String> descList = Lists.newArrayList(desc);
        descMap.put("desc", descList);
        Map<String, Map<String, List<String>>> map = new HashMap<>();
        map.put("contentMap", contentMap);
        map.put("desc", descMap);
        byte[] bytes = new byte[0];
        OutputStream os = null;
        try {
            bytes = htmlConvertImgHelper.htmlConvertImg("sizeImageTemplate.ftl", map, "jpg");
            os = new FileOutputStream(path);
            os.write(bytes, 0, bytes.length);
            os.flush();
            os.close();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param contentMap
     * @param titleTime
     * @param path
     */
    public void createTableImage(Map<String, List<String>> contentMap, String titleTime, String path) {
        Map<String, List<String>> descMap = new HashMap<>();
        List<String> descList = Lists.newArrayList(titleTime);
        descMap.put("dateTime", descList);
        Map<String, Map<String, List<String>>> map = new HashMap<>();
        map.put("contentMap", contentMap);
        map.put("dateTime", descMap);
        byte[] bytes = new byte[0];
        OutputStream os = null;
        try {
            bytes = htmlConvertImgHelper.htmlConvertImg("personal-pronouns.ftl", map, "jpg");
            os = new FileOutputStream(path);
            os.write(bytes, 0, bytes.length);
            os.flush();
            os.close();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param contentMap
     * @param titleTime
     * @param path
     */
    public void createImageByTable(Map<String, List<String>> contentMap, String titleTime, String path, String fileName) {
        Map<String, List<String>> descMap = new HashMap<>();
        List<String> descList = Lists.newArrayList(titleTime);
        descMap.put("dateTime", descList);
        Map<String, Map<String, List<String>>> map = new HashMap<>();
        map.put("contentMap", contentMap);
        map.put("dateTime", descMap);
        byte[] bytes = new byte[0];
        OutputStream os = null;
        try {
            bytes = htmlConvertImgHelper.htmlConvertImg(fileName, map, "jpg", contentMap.size());
            os = new FileOutputStream(path);
            os.write(bytes, 0, bytes.length);
            os.flush();
            os.close();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
