package com.java.tutorial.project.controller;

import cn.hutool.core.util.RandomUtil;
import com.java.tutorial.project.domain.DouyinBloggerInfo;
import com.java.tutorial.project.mapper.DouyinBloggerInfoMapper;
import com.java.tutorial.project.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author meta
 */
@RestController
@RequestMapping("/talent")
public class RandomInsertController {


    @Resource
    private DouyinBloggerInfoMapper douyinBloggerInfoMapper;

    @GetMapping("/getTalentSchema")
    public Result<Object> getTalentSchema() {

        List<DouyinBloggerInfo> douyinBloggerInfos = douyinBloggerInfoMapper.selectAll();

        return Result.success(douyinBloggerInfos);
    }


    @PostMapping("/update")
    public Result<Object> updateTalentSchema(@RequestParam Long id) {

        DouyinBloggerInfo douyinBloggerInfo1 = douyinBloggerInfoMapper.selectOneById(id);

        String remark = RandomUtil.randomString(50);
        douyinBloggerInfo1.setRemark(remark);

        int update = douyinBloggerInfoMapper.update(douyinBloggerInfo1);

        return Result.success(remark);
    }


    @PostMapping
    public Result<Object> insertTalentSchema() throws IllegalAccessException {


        DouyinBloggerInfo douyinBloggerInfo = new DouyinBloggerInfo();

        fillRandomValues(douyinBloggerInfo);

        douyinBloggerInfoMapper.insert(douyinBloggerInfo);

        return Result.success(douyinBloggerInfo);
    }



    private static final Random RANDOM = RandomUtil.getRandom();

    public static void fillRandomValues(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fillRandomValue(obj, field);
        }
    }

    private static void fillRandomValue(Object obj, Field field) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        Object value = null;
        if (fieldType.equals(String.class)) {

            value = RandomUtil.randomString(50);

        } else if (fieldType.equals(Integer.TYPE) || fieldType.equals(Integer.class)) {
            value = RandomUtil.randomInt(100);
        } else if (fieldType.equals(Double.TYPE) || fieldType.equals(Double.class)) {
            value = RandomUtil.randomDouble(100000.99);
        } else if (fieldType.equals(Boolean.TYPE) || fieldType.equals(Boolean.class)) {
            value = RandomUtil.randomBoolean();
        } else if (fieldType.equals(Date.class)) {
            value = RandomUtil.randomDay(1, 1000);
        } else if (fieldType.equals(Long.TYPE) || fieldType.equals(Long.class)) {

            if (field.getName().equals("id")) {
                value = null;
            } else {
                value = RandomUtil.randomLong(10000000000L);
            }
        }
        if (value != null) {
            field.set(obj, value);
        }
    }
}
