package com.java.tutorial.project.config;

import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

/**
 * @author meta
 */
@Slf4j
@Intercepts(
    value = {@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Method method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        log.info("method:{}, args:{}", method, args);

        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        Object obj = boundSql.getParameterObject();
        String sql = boundSql.getSql();

        if (sql.trim().toUpperCase().startsWith("INSERT")) {
            ReflectUtil.setFieldValue(obj, "rev", 0);
            ReflectUtil.setFieldValue(obj, "createTime", new Date());
            ReflectUtil.setFieldValue(obj, "operateTime", new Date());
            ReflectUtil.setFieldValue(boundSql, "parameterObject", obj);

        } else if (sql.trim().toUpperCase().startsWith("UPDATE")) {
            sql = sql.replaceAll(" set ", " SET ").replaceAll(" Set ", " SET ")
                .replaceAll(" SET ", " SET rev = rev+1, operate_time = NOW(), ");
            ReflectUtil.setFieldValue(boundSql, "sql", sql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
