package com.java.tutorial.tool.domain;

import cn.hutool.core.util.RandomUtil;

public class TestDemo {

    public static void main(String[] args) {


        MessageDto messageDto = MessageDtoBuilder.builderMessageDto(12L, RandomUtil.randomString(5), RandomUtil.randomString(5));


    }
}
