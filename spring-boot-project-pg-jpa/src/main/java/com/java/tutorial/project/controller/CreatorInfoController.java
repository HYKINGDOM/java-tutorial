package com.java.tutorial.project.controller;

import com.java.coco.common.Result;
import com.java.tutorial.project.domain.CreatorInfo;
import com.java.tutorial.project.service.CreatorInfoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创作者信息控制层
 * @author meta
 */
@RestController
public class CreatorInfoController {

    @Resource
    private CreatorInfoService creatorInfoService;

    /**
     * 通过GET请求获取创作者信息
     *
     * @param creatorId 创作者的ID，用于标识需要获取信息的创作者
     * @return 返回一个Result对象，包含创作者信息成功获取的结果
     *
     * 说明：
     * 1. 该方法通过HTTP GET请求方式提供服务，请求的路径为"/getCreatorInfo"
     * 2. 通过@RequestParam注解，将请求参数"creatorId"的值绑定到方法参数creatorId中
     * 3. 方法内部调用服务层的getCreatorInfoByCreatorId方法，通过传入的creatorId获取创作者信息
     * 4. 最后，通过Result.success方法包装返回结果，确保返回结构统一且包含成功获取的创作者信息
     */
    @GetMapping("/getCreatorInfo")
    public Result<CreatorInfo> getCreatorInfo(@RequestParam("creatorId") String creatorId) {
        return Result.success(creatorInfoService.getCreatorInfoByCreatorId(creatorId));
    }

    /**
     * 通过QueryDSL查询创作者信息
     * @param creatorId 创作者的ID，用于标识需要获取信息的创作者
     * @return 返回一个Result对象，包含创作者信息成功获取的结果
     */
    @GetMapping("/queryDslCreatorInfoByCreatorId")
    public Result<CreatorInfo> queryDslCreatorInfoByCreatorId(@RequestParam("creatorId") String creatorId) {
        return Result.success(creatorInfoService.queryDslCreatorInfoByCreatorId(creatorId));
    }

    /**
     * 根据国家名称查询CreatorInfo分页数据
     *
     * @param country 国家名称，用于筛选CreatorInfo
     * @param pageNumber 页码，用于分页查询
     * @return 返回查询结果，包含分页的CreatorInfo数据
     */
    @GetMapping("/findByCountry")
    public Result<Page<CreatorInfo>> findByCountry(@RequestParam("country") String country,
        @RequestParam("pageNumber") Integer pageNumber) {
        // 调用服务层方法根据国家名称和页码查询CreatorInfo数据，并封装成统一结果格式返回
        return Result.success(creatorInfoService.findByCountry(country, pageNumber));
    }

    /**
     * 查询所有创建者的ID
     *
     * @return 返回一个包含所有创建者ID的列表的结果对象
     */
    @GetMapping("/queryAllCreatorId")
    public Result<List<String>> queryAllCreatorId() {
        // 调用creatorInfoService查询所有创建者的ID
        List<String> stringList = creatorInfoService.queryAllCreatorId();
        // 返回查询结果，使用Result的成功状态封装
        return Result.success(stringList);
    }

    /**
     * 更新创作者信息updateCreatorInfo
     *
     * @param creatorInfo 创作者信息对象，包含待更新的属性
     * @return 返回更新结果的封装对象，包含状态码和消息
     */
    @PostMapping("/updateCreatorInfo")
    public Result<CreatorInfo> updateCreatorInfo(@RequestBody CreatorInfo creatorInfo) {
        // 调用服务层方法更新创作者信息
        creatorInfoService.updateCreatorInfo(creatorInfo);
        // 返回成功结果
        return Result.success();
    }


    /**
     * 更新创作者信息updateQueryDslCreatorInfo
     *
     * @param creatorInfo 创作者信息对象，包含待更新的属性
     * @return 返回更新结果的封装对象，包含状态码和消息
     */
    @PostMapping("/updateQueryDslCreatorInfo")
    public Result<CreatorInfo> updateQueryDslCreatorInfo(@RequestBody CreatorInfo creatorInfo) {
        // 调用服务层方法更新创作者信息
        creatorInfoService.updateQueryDslCreatorInfo(creatorInfo);
        // 返回成功结果
        return Result.success();
    }

}
