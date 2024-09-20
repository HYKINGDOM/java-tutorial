package com.java.tutorial.project.controller;

import com.java.coco.common.Result;
import com.java.tutorial.project.domain.HandlerResult;
import com.java.tutorial.project.service.ArticleModifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author meta
 */
@RestController
public class ArticleModifyController {

    @Resource
    private ArticleModifyService articleModifyService;

    @GetMapping("/test")
    public Result<HandlerResult> modifyArticle() {
        return Result.success(articleModifyService.handle());
    }

}
