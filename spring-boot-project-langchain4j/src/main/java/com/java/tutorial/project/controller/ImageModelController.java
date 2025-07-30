package com.java.tutorial.project.controller;


import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesis;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisParam;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

/**
 * @author meta
 */
@RequestMapping("/langchain4j/V4")
@RestController
@Slf4j
public class ImageModelController {


    @jakarta.annotation.Resource
    private ChatModel imageModel;

    @Value("classpath:static/images/mi.jpg")
    private Resource resource;

    /**
     * 通过Base64编码将图片转化为字符串，结合ImageContent和TextContent一起发送到模型进行处理。
     *
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/image/call")
    public String readImageContent() throws IOException {

        byte[] byteArray = resource.getContentAsByteArray();
        String base64Data = Base64.getEncoder().encodeToString(byteArray);

        UserMessage userMessage = UserMessage.from(
                TextContent.from("从以下图片中获取来源网站名称，股价走势和5月30号股价"),
                ImageContent.from(base64Data, "image/jpg")
        );

        ChatResponse chatResponse = imageModel.chat(userMessage);
        String result = chatResponse.aiMessage().text();

        System.out.println(result);

        return result;
    }


    @GetMapping(value = "/image/create3")
    public String createImageContent3() {

        String prompt = "近景镜头，18岁的法国女孩，古代服饰，圆脸，正面看着镜头，民族优雅的服装，商业摄影，室外，电影级光照，半身特写，精致的淡妆，锐利的边缘。";
        ImageSynthesisParam param =
                ImageSynthesisParam.builder()
                        .apiKey(System.getenv("aliQwen-api"))
                        .model(ImageSynthesis.Models.WANX_V1)
                        .prompt(prompt)
                        .style("<watercolor>")
                        .n(1)
                        .size("1024*1024")
                        .build();

        ImageSynthesis imageSynthesis = new ImageSynthesis();
        ImageSynthesisResult result = null;
        try {
            System.out.println("---sync call, please wait a moment----");
            result = imageSynthesis.call(param);
        } catch (ApiException | NoApiKeyException e) {
            throw new RuntimeException(e.getMessage());
        }


        System.out.println(JsonUtils.toJson(result));

        return JsonUtils.toJson(result);
    }
}
