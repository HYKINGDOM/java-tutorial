package com.java.tutorial.project.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.java.coco.common.ErrorCode;
import com.java.coco.common.Result;
import com.java.tutorial.project.domain.TalentSchema;
import com.java.tutorial.project.domain.TalentUser;
import com.java.tutorial.project.domain.mysql.CrmContactStatisticsEntity;
import com.java.tutorial.project.domain.schema.UserInfo;
import com.java.tutorial.project.mapper.CrmContactStatisticsMapper;
import com.java.tutorial.project.mapper.TalentSchemaMapper;
import com.java.tutorial.project.mapper.TalentUserMapper;
import com.java.tutorial.project.mapper.UserInfoMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableManager;
import com.mybatisflex.core.update.UpdateChain;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/talent")
public class TalentSchemaController {

    @Resource
    private TalentSchemaMapper talentSchemaMapper;

    @Resource
    private TalentUserMapper talentUserMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private CrmContactStatisticsMapper crmContactStatisticsMapper;

    @GetMapping
    public Result<Object> getTalentSchema() {

        List<TalentSchema> talentSchemas = talentSchemaMapper.selectAll();

        return Result.success(talentSchemas);
    }

    @GetMapping("/getMysqlData")
    public Result<Object> getMysqlData() {

        List<CrmContactStatisticsEntity> crmContactStatisticsEntities = crmContactStatisticsMapper.selectAll();

        return Result.success(crmContactStatisticsEntities);
    }

    @PostMapping
    public Result<Object> login(@RequestBody TalentUser talentUser) {

        QueryWrapper queryWrapper = QueryWrapper.create().select().from(TalentUser.class).where(TalentUser::getUserId)
            .eq(talentUser.getUserId());

        TalentUser talentUser1 = talentUserMapper.selectOneByQuery(queryWrapper);

        if (talentUser1 == null || talentUser.getUserId() == null) {

            return Result.failure(ErrorCode.FAIL);
        }

        List<TalentSchema> talentSchemas = talentSchemaMapper.selectAll();

        List<TalentSchema> talentSchemaList =
            talentSchemas.stream().filter(talentSchema -> talentSchema.getTalentId().equals(talentUser1.getTalentId()))
                .collect(Collectors.toList());

        if (CollectionUtil.isEmpty(talentSchemaList)) {
            return Result.failure(ErrorCode.FAIL);
        }

        TalentSchema talentSchema = talentSchemaList.get(0);

        TableManager.setDynamicSchemaProcessor((schema, table) -> talentSchema.getSchemaName());

        QueryWrapper queryWrapperUserInfo =
            QueryWrapper.create().select().from(UserInfo.class).where(UserInfo::getUserId).eq(talentUser1.getUserId());

        UserInfo userInfo = userInfoMapper.selectOneByQuery(queryWrapperUserInfo);

        TableManager.setDynamicSchemaProcessor((schema, table) -> "public");

        return Result.success(userInfo);
    }

    @PostMapping("/updatePassword")
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> updatePassword(@RequestBody UserInfo userInfo) {

        QueryWrapper queryWrapper =
            QueryWrapper.create().select().from(TalentUser.class).where(TalentUser::getUserId).eq(userInfo.getUserId());

        TalentUser talentUser1 = talentUserMapper.selectOneByQuery(queryWrapper);

        if (talentUser1 == null || userInfo.getUserId() == null) {

            return Result.failure(ErrorCode.FAIL);
        }

        List<TalentSchema> talentSchemas = talentSchemaMapper.selectAll();

        List<TalentSchema> talentSchemaList =
            talentSchemas.stream().filter(talentSchema -> talentSchema.getTalentId().equals(talentUser1.getTalentId()))
                .collect(Collectors.toList());

        if (CollectionUtil.isEmpty(talentSchemaList)) {
            return Result.failure(ErrorCode.FAIL);
        }

        TalentSchema talentSchema = talentSchemaList.get(0);

        TableManager.setDynamicSchemaProcessor((schema, table) -> talentSchema.getSchemaName());

        QueryWrapper queryWrapperUserInfo =
            QueryWrapper.create().select().from(UserInfo.class).where(UserInfo::getUserId).eq(talentUser1.getUserId());

        UserInfo userInfoResult = userInfoMapper.selectOneByQuery(queryWrapperUserInfo);

        UpdateChain.of(UserInfo.class).set(UserInfo::getPassword, userInfo.getPassword()).where(UserInfo::getUserId)
            .eq(userInfoResult.getUserId());

        TableManager.setDynamicSchemaProcessor((schema, table) -> "public");


        if (true) {

            throw new RuntimeException("更新异常!");
        }


        return Result.success(userInfo);
    }
}
