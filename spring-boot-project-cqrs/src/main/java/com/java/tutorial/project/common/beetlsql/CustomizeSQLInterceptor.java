package com.java.tutorial.project.common.beetlsql;

import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.InterceptorContext;

/**
 * 定制SQL可实现多租户数据隔离等需求
 */
public final class CustomizeSQLInterceptor implements Interceptor {

    @Override
    public void before(InterceptorContext ctx) {
        // ExecuteContext context = ctx.getExecuteContext();
        // String jdbcSql = context.sqlResult.jdbcSql;
        // if (jdbcSql.indexOf("count") == -1) {
        // context.sqlResult.jdbcSql = jdbcSql.replace("*", "code,name");
        // }
    }

    @Override
    public void after(InterceptorContext ctx) {
    }

    @Override
    public void exception(InterceptorContext ctx, Exception ex) {
    }

}
