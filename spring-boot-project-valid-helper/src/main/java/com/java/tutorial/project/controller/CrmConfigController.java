package com.java.tutorial.project.controller;

import com.github.houbb.validator.api.api.result.IResult;
import com.github.houbb.validator.core.util.ValidHelper;
import com.java.coco.common.Result;
import com.java.tutorial.project.infrastructure.domain.CrmConfig;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class CrmConfigController {

    @GetMapping("/list")
    public Result<Object> list(@Validated @RequestBody CrmConfig crmConfig) {

        IResult result = ValidHelper.failFast(crmConfig);

        return Result.success();
    }
}
