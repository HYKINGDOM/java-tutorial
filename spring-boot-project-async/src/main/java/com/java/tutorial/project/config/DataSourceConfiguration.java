package com.java.tutorial.project.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author hy
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 用户数据源配置(主数据源)
     *
     * @return
     */
    @Bean("userDatasourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 用户数据源(主数据源)
     *
     * @return
     */
    @Bean("userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource userDataSource() {
        DataSourceProperties properties = this.userDataSourceProperties();
        return createHikariDataSource(properties);
    }



    /**
     * 创建 Hikari 数据库连接池
     *
     * @param properties
     * @return
     */
    private HikariDataSource createHikariDataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

}

