package com.java.tutorial.tool.domain;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageDtoBuilderTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void test_MessageDtoBuilderTest() {

        MessageDto messageDto =
            MessageDtoBuilder.builderMessageDto(12L, RandomUtil.randomString(5), RandomUtil.randomString(5));

    }
}