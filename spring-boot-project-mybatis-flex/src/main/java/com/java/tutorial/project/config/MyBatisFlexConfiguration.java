package com.java.tutorial.project.config;

import com.java.tutorial.project.config.audit.SQLAuditLogInterceptor;
import com.java.tutorial.project.config.listener.EntityInsertListener;
import com.java.tutorial.project.config.listener.EntityUpdateListener;
import com.java.tutorial.project.domain.BaseEntity;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisFlex 配置
 * @author meta
 */
@Configuration
public class MyBatisFlexConfiguration implements ConfigurationCustomizer, MyBatisFlexCustomizer {

    private static final Logger logger = LoggerFactory.getLogger("mybatis-flex-sql");

    public MyBatisFlexConfiguration() {
        //开启审计功能
        AuditManager.setAuditEnable(true);
        //设置审计工厂创建审计消息
        //        AuditManager.setMessageFactory(new MybatisFlexAuditMessageFactory());
        //设置 自定义 SQL 审计收集器
        AuditManager.setMessageCollector(
            auditMessage -> logger.info("{},{}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime()));
    }

    /**
     * 自定义配置
     *
     * @param configuration MyBatis Flex Configuration
     */
    @Override
    public void customize(FlexConfiguration configuration) {
        //mybatis实现的打印sql到控制台，便于调试
        configuration.setLogImpl(StdOutImpl.class);
        configuration.addInterceptor(new SQLAuditLogInterceptor());
    }

    @Override
    public void customize(FlexGlobalConfig flexGlobalConfig) {
        flexGlobalConfig.registerInsertListener(new EntityInsertListener(), BaseEntity.class);
        flexGlobalConfig.registerUpdateListener(new EntityUpdateListener(), BaseEntity.class);
    }
}