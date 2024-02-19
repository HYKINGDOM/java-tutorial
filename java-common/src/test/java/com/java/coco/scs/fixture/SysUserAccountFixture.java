package com.java.coco.scs.fixture;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.java.coco.domian.SysUserAccount;
import com.java.coco.utils.RanDomUtils;

import java.util.Date;
import java.util.List;

import static cn.hutool.core.date.DateField.DAY_OF_WEEK_IN_MONTH;

public class SysUserAccountFixture {

    public static List<SysUserAccount> buildSysUserAccount(int size) {

        List<SysUserAccount> newArrayList = Lists.newArrayList();

        for (int i = 0; i < size; i++) {

            SysUserAccount sysUserAccount =
                SysUserAccount.builder().id(RandomUtil.randomLong(1, 3)).userName(RandomUtil.randomString(10))
                    .userId(RandomUtil.randomInt(2)).delFlag(RandomUtil.randomInt(2)).status(RandomUtil.randomInt(2))
                    .email(RandomUtil.randomString("@", 18)).mobilePhone(RanDomUtils.randomNumeric(13))
                    .vision(RandomUtil.randomInt(2)).createdBy(RandomUtil.randomString(10))
                    .createdTime(RandomUtil.randomDate(new Date(), DAY_OF_WEEK_IN_MONTH, 1, 10))
                    .updatedBy(RandomUtil.randomString(10))
                    .updatedTime(RandomUtil.randomDate(new Date(), DAY_OF_WEEK_IN_MONTH, 1, 10)).build();

            newArrayList.add(sysUserAccount);
        }

        return newArrayList;
    }

}
