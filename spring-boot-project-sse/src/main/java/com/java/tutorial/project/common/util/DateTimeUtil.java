package com.java.tutorial.project.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;

import java.time.LocalDateTime;

/**
 * @author meta
 */
public interface DateTimeUtil {

    Long TIMEOUT = 600000L;

    String FORMAT_DATE_STR = "yyyy-MM-dd HH:mm:ss";

    String FORMAT_DATE_TIME = DateUtil.format(LocalDateTime.now(), FORMAT_DATE_STR);

    String FORMAT_LAST_DATE_TIME = DateUtil.format(LocalDateTime.now().plusSeconds(TIMEOUT), FORMAT_DATE_STR);

    /**
     * 获取当前时间与当前时间加TIMEOUT时间，返回时间格式为yyyy-MM-dd HH:mm:ss
     *
     * @return Pair<String, String>
     */
    default Pair<String, String> getStartTimeAndEndTimeFormatDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return new Pair<>(DateUtil.format(localDateTime, FORMAT_DATE_STR),
            DateUtil.format(localDateTime.plusSeconds(TIMEOUT), FORMAT_DATE_STR));
    }

}
