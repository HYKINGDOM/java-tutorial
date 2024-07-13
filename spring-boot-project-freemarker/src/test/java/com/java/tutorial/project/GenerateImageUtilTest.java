package com.java.tutorial.project;

import com.google.common.collect.Lists;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class GenerateImageUtilTest {

    @Resource
    private GenerateImageUtil generateImageUtil;

    @Test
    public void ccc() throws IOException, TemplateException {

        Map<String, List<String>> map1 = new HashMap<>();
        List<String> title1 =
            Lists.newArrayList("Size", "Length", "Sleeve Length", "Bust", "Waist Size", "Hip Size", "Cuff");
        List<String> title2 = Lists.newArrayList("XS", "44.1", "4.3", "20.5-45.7", "21.3-45.7", "49.6", "9.4-22.4");
        List<String> title3 = Lists.newArrayList("S", "44.9", "4.5", "22-47.2", "22.8-47.2", "51.2", "9.8-22.8");
        List<String> title4 = Lists.newArrayList("M", "45.7", "4.7", "23.6-48.8", "24.4-48.8", "52.8", "10.3-23.3");
        List<String> title5 = Lists.newArrayList("L", "46.5", "5", "26-51.2", "26.8-51.2", "55.1", "10.9-23.9");
        map1.put("title1", title1);
        map1.put("title2", title2);
        map1.put("title3", title3);
        map1.put("title4", title4);
        map1.put("title5", title5);

        generateImageUtil.createSizeTableImage(map1,
            "*This data was obtained from manually measuring the product, it may be off by 1-2 IN.",
            "E:\\sqlite\\test1.jpg");
    }

    @Test
    public void create() {

        Map<String, List<String>> map1 = new HashMap<>();
        List<String> title1 =
            Lists.newArrayList("新开一组", "张三无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title2 =
            Lists.newArrayList("新开二组", "三无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title3 =
            Lists.newArrayList("新开三组", "李四", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title4 =
            Lists.newArrayList("新开四组", "张三", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title5 =
            Lists.newArrayList("新开五组", "张无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        map1.put("title1", title1);
        map1.put("title2", title2);
        map1.put("title3", title3);
        map1.put("title4", title4);
        map1.put("title5", title5);

        generateImageUtil.createTableImage(map1, "2023-04-15", "E:\\sqlite\\test2.jpg");
    }

    @Test
    public void create_01() {

        Map<String, List<String>> map1 = new HashMap<>();
        List<String> title1 =
            Lists.newArrayList("新开一组", "张三无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title2 =
            Lists.newArrayList("新开二组", "三无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title3 =
            Lists.newArrayList("新开三组", "李四", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title4 =
            Lists.newArrayList("新开四组", "张三", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title5 =
            Lists.newArrayList("新开五组", "张无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title6 =
            Lists.newArrayList("新开一组", "张三无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title7 =
            Lists.newArrayList("新开二组", "三无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title8 =
            Lists.newArrayList("新开三组", "李四", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title9 =
            Lists.newArrayList("新开四组", "张三", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title10 =
            Lists.newArrayList("新开五组", "张无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title11 =
            Lists.newArrayList("新开一组", "张三无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title12 =
            Lists.newArrayList("新开二组", "三无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title13 =
            Lists.newArrayList("新开三组", "李四", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title14 =
            Lists.newArrayList("新开四组", "张三", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title15 =
            Lists.newArrayList("新开五组", "张无", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        map1.put("title1", title1);
        map1.put("title2", title2);
        map1.put("title3", title3);
        map1.put("title4", title4);
        map1.put("title5", title5);
        map1.put("title6", title6);
        map1.put("title7", title7);
        map1.put("title8", title8);
        map1.put("title9", title9);
        map1.put("title10", title10);
        map1.put("title11", title11);
        map1.put("title12", title12);
        map1.put("title13", title13);
        map1.put("title14", title14);
        map1.put("title15", title15);

        generateImageUtil.createTableImage(map1, "2023-04-15", "E:\\sqlite\\test2.jpg");
    }

    @Test
    public void create_02() {

        Map<String, List<String>> map1 = new LinkedHashMap<>();
        List<String> title1 =
            Lists.newArrayList("新开一组", "张三无1", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title2 =
            Lists.newArrayList("新开二组", "三无2", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title3 =
            Lists.newArrayList("新开三组", "李四3", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title4 =
            Lists.newArrayList("新开四组", "张三4", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title5 =
            Lists.newArrayList("合计", "-", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30");
        List<String> title6 =
            Lists.newArrayList("新开一组", "张三无6", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title7 =
            Lists.newArrayList("新开二组", "三无7", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title8 =
            Lists.newArrayList("新开三组", "李四8", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title9 =
            Lists.newArrayList("合计", "-", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30");
        List<String> title10 =
            Lists.newArrayList("新开五组", "张无10", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title11 =
            Lists.newArrayList("新开一组", "张三无11", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title12 =
            Lists.newArrayList("新开二组", "三无12", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title13 =
            Lists.newArrayList("新开三组", "李四13", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title14 =
            Lists.newArrayList("新开四组", "张三14", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30", "30");
        List<String> title15 =
            Lists.newArrayList("合计", "-", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30");
        List<String> title16 =
            Lists.newArrayList("总计", "-", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30",
                "30");
        map1.put("title1", title1);
        map1.put("title2", title2);
        map1.put("title3", title3);
        map1.put("title4", title4);
        map1.put("合计5", title5);
        map1.put("title6", title6);
        map1.put("title7", title7);
        map1.put("title8", title8);
        map1.put("合计9", title9);
        map1.put("title10", title10);
        map1.put("title11", title11);
        map1.put("title12", title12);
        map1.put("title13", title13);
        map1.put("title14", title14);
        map1.put("合计15", title15);
        map1.put("总计16", title16);

        generateImageUtil.createSizeTableImage(map1, "2023-04-18", "E:\\sqlite\\test3.jpg");
    }

}