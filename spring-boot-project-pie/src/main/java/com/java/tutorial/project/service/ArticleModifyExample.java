package com.java.tutorial.project.service;

import com.feiniaojin.ddd.ecosystem.pie.BootStrap;
import com.java.tutorial.project.domain.ArticleTitleModifyDto;
import com.java.tutorial.project.domain.HandlerResult;
import com.java.tutorial.project.factory.ArticleModifyChannel;
import com.java.tutorial.project.factory.HandlerResultFactory;
import com.java.tutorial.project.handlers.ArticleModifyContentHandler;
import com.java.tutorial.project.handlers.ArticleModifyTitleHandler;
import com.java.tutorial.project.handlers.CheckParameterHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author meta
 */
@Slf4j
public class ArticleModifyExample {

    public static void main(String[] args) {
        //入参
        ArticleTitleModifyDto dto = new ArticleTitleModifyDto();
        dto.setArticleId("articleId_001");
        dto.setTitle("articleId_001_title");
        dto.setContent("articleId_001_content");
        //创建引导类
        BootStrap bootStrap = new BootStrap();

        HandlerResult handlerResult = (HandlerResult)bootStrap.inboundParameter(dto)//入参
            .outboundFactory(new HandlerResultFactory())//出参工厂
            .channel(new ArticleModifyChannel())//自定义channel
            .addChannelHandlerAtLast("checkParameter", new CheckParameterHandler())//第一个handler
            .addChannelHandlerAtLast("modifyTitle", new ArticleModifyTitleHandler())//第二个handler
            .addChannelHandlerAtLast("modifyContent", new ArticleModifyContentHandler())//第三个handler
            .process();//执行
        //result为执行结果
        log.info("result:code={},msg={}, data:{}", handlerResult.getCode(), handlerResult.getMsg(), dto);
    }
}
