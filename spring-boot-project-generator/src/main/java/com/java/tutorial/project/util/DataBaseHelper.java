package com.java.tutorial.project.util;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.dialect.DbType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 数据库助手：判断数据库类型
 *
 * @author meta
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBaseHelper {

    public static boolean isMySql() {
        return DbType.MYSQL == FlexGlobalConfig.getDefaultConfig().getDbType();
    }

    public static boolean isPostgreSql() {
        return DbType.POSTGRE_SQL == FlexGlobalConfig.getDefaultConfig().getDbType();
    }
}
