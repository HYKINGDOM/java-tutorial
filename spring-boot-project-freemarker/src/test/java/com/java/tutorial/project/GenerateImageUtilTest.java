package com.java.tutorial.project;

import com.google.common.collect.Lists;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Slf4j
class GenerateImageUtilTest {


    @Resource
    private GenerateImageUtil generateImageUtil;


    @Test
    public void ccc() throws IOException, TemplateException {

        Map<String, List<String>> map1 = new HashMap<>();
        List<String> title1 = Lists.newArrayList("Size", "Length", "Sleeve Length", "Bust", "Waist Size", "Hip Size", "Cuff");
        List<String> title2 = Lists.newArrayList("XS", "44.1", "4.3", "20.5-45.7", "21.3-45.7", "49.6", "9.4-22.4");
        List<String> title3 = Lists.newArrayList("S", "44.9", "4.5", "22-47.2", "22.8-47.2", "51.2", "9.8-22.8");
        List<String> title4 = Lists.newArrayList("M", "45.7", "4.7", "23.6-48.8", "24.4-48.8", "52.8", "10.3-23.3");
        List<String> title5 = Lists.newArrayList("L", "46.5", "5", "26-51.2", "26.8-51.2", "55.1", "10.9-23.9");
        map1.put("title1", title1);
        map1.put("title2", title2);
        map1.put("title3", title3);
        map1.put("title4", title4);
        map1.put("title5", title5);

        generateImageUtil.createSizeTableImage(map1, "*This data was obtained from manually measuring the product, it may be off by 1-2 IN.", "E:\\sqlite\\test1.jpg");
    }

}