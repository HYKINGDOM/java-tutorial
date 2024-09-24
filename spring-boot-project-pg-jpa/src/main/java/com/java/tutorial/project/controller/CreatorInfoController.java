package com.java.tutorial.project.controller;

import com.java.coco.common.Result;
import com.java.tutorial.project.domain.CreatorInfo;
import com.java.tutorial.project.service.CreatorInfoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CreatorInfoController {

    @Resource
    private CreatorInfoService creatorInfoService;

    @GetMapping("/getCreatorInfo")
    public Result<CreatorInfo> getCreatorInfo(@RequestParam("creatorId") String creatorId) {
        return Result.success(creatorInfoService.getCreatorInfoByCreatorId(creatorId));
    }


    @GetMapping("/findByCountry")
    public Result<Page<CreatorInfo>> findByCountry(@RequestParam("country") String country, @RequestParam("pageNumber") Integer pageNumber) {
        return Result.success(creatorInfoService.findByCountry(country,pageNumber));
    }

}
