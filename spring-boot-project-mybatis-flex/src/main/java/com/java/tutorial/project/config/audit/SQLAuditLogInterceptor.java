package com.java.tutorial.project.config.audit;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.java.tutorial.project.config.audit.handle.MySqlDeleteSqlAuditHandler;
import com.java.tutorial.project.config.audit.handle.MySqlInsertSQLAuditHandler;
import com.java.tutorial.project.config.audit.handle.MySqlUpdateSQLAuditHandler;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class SQLAuditLogInterceptor implements Interceptor {

    private static final Pattern pattern1 = Pattern.compile("\\?(?=([^']*'[^']*')*[^']*$)");

    private static final Pattern pattern2 = Pattern.compile("[\\s]+");


    private Boolean auditEnable = Boolean.TRUE;

    private DBMetaDataHolder dbMetaDataHolder;

    private Method clerkIdMethod;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        initData();
        if (auditEnable && invocation.getArgs()[0] instanceof MappedStatement) {
            MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
            String sqlCommandType = mappedStatement.getSqlCommandType().name();
            if (AuditOperationEnum.insert.name()
                .equalsIgnoreCase(sqlCommandType) || AuditOperationEnum.update.name()
                .equalsIgnoreCase(sqlCommandType) || AuditOperationEnum.delete.name()
                .equalsIgnoreCase(sqlCommandType)) {
                ISQLHandler sqlAuditHandler = null;
                try {
                    Executor executor = (Executor)invocation.getTarget();
                    Connection connection = executor.getTransaction().getConnection();
                    dbMetaDataHolder.init(connection);
                    Object parameter = null;
                    if (invocation.getArgs().length > 1) {
                        parameter = invocation.getArgs()[1];
                    }
                    BoundSql boundSql = mappedStatement.getBoundSql(parameter);
                    Configuration configuration = mappedStatement.getConfiguration();
                    String sql = getParameterizedSql(configuration, boundSql);
                    if (AuditOperationEnum.insert.name().equalsIgnoreCase(sqlCommandType)) {
                        sqlAuditHandler =
                            new MySqlInsertSQLAuditHandler(connection, dbMetaDataHolder, clerkIdMethod, sql);
                    } else if (AuditOperationEnum.update.name().equalsIgnoreCase(sqlCommandType)) {
                        sqlAuditHandler =
                            new MySqlUpdateSQLAuditHandler(connection, dbMetaDataHolder, clerkIdMethod, sql);
                    } else if (AuditOperationEnum.delete.name().equalsIgnoreCase(sqlCommandType)) {
                        sqlAuditHandler =
                            new MySqlDeleteSqlAuditHandler(connection, dbMetaDataHolder, clerkIdMethod, sql);
                    }
                    if (sqlAuditHandler != null) {
                        sqlAuditHandler.preHandle();
                    }
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
                Object result = invocation.proceed();
                try {
                    if (sqlAuditHandler != null) {
                        sqlAuditHandler.postHandle();
                    }
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
                return result;
            }
        }
        return invocation.proceed();
    }

    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "null";
            }

        }
        return value;
    }

    private String getParameterizedSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = pattern2.matcher(boundSql.getSql()).replaceAll(" ");
        if (CollectionUtil.isNotEmpty(parameterMappings) && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            // 修复：完全重构参数替换逻辑
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                String value = getParameterValue(parameterObject);
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(value));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                // 使用StringBuilder来逐个替换参数
                StringBuilder sqlBuilder = new StringBuilder(sql);
                int startIndex = 0;
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    String value;
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        value = getParameterValue(obj);
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        value = getParameterValue(obj);
                    } else {
                        value = "''"; // 默认空字符串
                    }

                    // 查找下一个问号位置并替换
                    int index = sqlBuilder.indexOf("?", startIndex);
                    if (index >= 0) {
                        sqlBuilder.replace(index, index + 1, Matcher.quoteReplacement(value));
                        startIndex = index + value.length();
                    }
                }
                sql = sqlBuilder.toString();
            }
        }
        return sql;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }


    public void initData() {
        // 是否进行自动分表，默认值: true
        Boolean splitEnableOption = Boolean.TRUE;
        // 不分表时的表名，表不存在将会自动新建，默认值: audit_log
        String defaultTableNameOption = "audit_log";
        // 分表时的表名前缀，每月自动新建一张，生成规则 前缀名+年月，默认值: audit_log_
        String preTableNameOption = "audit_log_";
        // 指定用于获取当前登录用户ID的方法，#号之前是类的全限定名称，#号之后是静态方法的名称，返回int类型，如不需要记录用户ID可删除此配置项
        String clerkIdMethodOption = "";

        if (StrUtil.isNotBlank(clerkIdMethodOption)) {
            try {
                String classAndMethodName[] = clerkIdMethodOption.split("#");
                if (classAndMethodName.length == 2) {
                    Class clerkClazz = Class.forName(classAndMethodName[0]);
                    clerkIdMethod = clerkClazz.getMethod(classAndMethodName[1]);
                }

            } catch (ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        // 是否开启审计功能，默认值: true
        auditEnable = Boolean.TRUE;
        dbMetaDataHolder = new DBMetaDataHolder(
            new AuditLogTableCreator(splitEnableOption, defaultTableNameOption, preTableNameOption));

    }

}
