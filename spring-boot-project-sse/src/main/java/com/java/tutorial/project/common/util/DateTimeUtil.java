package com.java.tutorial.project.common.util;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author meta 
 */
public interface DateTimeUtil {
    
    String FORMAT_DATE_TIME = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    
}
