package com.java.tutorial.project.service;

import com.feiniaojin.ddd.ecosystem.pie.BootStrap;
import com.java.tutorial.project.domain.ArticleTitleModifyDto;
import com.java.tutorial.project.domain.HandlerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Slf4j
@Service
public class ArticleModifyService {

    @Resource
    private BootStrap totalHandlerResult;

    public HandlerResult handle() {
        //入参
        ArticleTitleModifyDto dto = new ArticleTitleModifyDto();
        dto.setArticleId("articleId_002");
        dto.setTitle("articleId_002_title");
        dto.setContent("articleId_002_content");

        HandlerResult handlerResult = (HandlerResult)totalHandlerResult.inboundParameter(dto).process();
        //result为执行结果
        log.info("result:code={},msg={}, data:{}", handlerResult.getCode(), handlerResult.getMsg(), dto);

        handlerResult.setData(dto);

        return handlerResult;
    }
}
