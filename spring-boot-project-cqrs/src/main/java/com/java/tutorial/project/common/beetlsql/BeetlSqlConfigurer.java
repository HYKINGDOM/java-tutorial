package com.java.tutorial.project.common.beetlsql;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.java.tutorial.project.common.auth.AccessContext;
import com.java.tutorial.project.common.redis.RedisCache;
import org.beetl.sql.core.IDAutoGen;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.beetl.sql.starter.SQLManagerCustomize;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import cn.beecp.BeeDataSource;

/**
 * BeetlSql配置信息
 */
@Configuration
public class BeetlSqlConfigurer {

    @Resource
    private RedisCache<Integer> cache;
    @Lazy
    @Resource
    private SQLManager primarySQLManager;

    @Bean
    // @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().type(BeeDataSource.class).build();
    }

    @Bean
    public SQLManagerCustomize sqlManagerCustomize() {
        Interceptor[] inters = primarySQLManager.getInters();
        Interceptor[] newInterceptors = new Interceptor[inters.length + 1];
        newInterceptors[0] = new CustomizeSQLInterceptor();
        for (int i = 0, length = inters.length; i < length; ++i) {
            newInterceptors[i + 1] = inters[i];
        }
        // primarySQLManager.setSqlManagerExtend(new CustomizeSQLManagerExtend());
        primarySQLManager.setInters(newInterceptors);
        // 防火墙功能
        // FireWall fireWall = new FireWall();
        // fireWall.setDmlCreateEnable(false);
        // fireWall.setSqlMaxLength(50);
        // FireWallConfig fireWallConfig = new FireWallConfig(fireWall);
        // fireWallConfig.config(primarySQLManager);

        return (sqlManagerName, manager) -> {

            // 将BigDecimal映射为BigInteger
            // BigIntTypeHandler bigIntTypeHandler = new BigIntTypeHandler();
            // manager.getDefaultBeanProcessors().addHandler(BigInteger.class,
            // bigIntTypeHandler);

            // 自动生成自增主键,微服务可以使用github上滴滴开源的Tinyid方案
            manager.addIdAutoGen("autoId", new IDAutoGen<Long>() {

                @Override
                public Long nextID(String params) {
                    String sql = "select nextval('all_id_seq')";
                    return primarySQLManager.executeQueryOne(new SQLReady(sql), Long.class);
                }

            });
            // 订单号
            manager.addIdAutoGen("orderId", new IDAutoGen<Long>() {

                @Override
                public Long nextID(String params) {
                    Date now = new Date();
                    String date = String.format("order-%tF", now);
                    if (!cache.hasKey(date)) {
                        cache.expire(date, 1, TimeUnit.DAYS);
                    }
                    Long count = cache.increment(date);
                    if (count < 100) {
                        Long remainderUserId = AccessContext.getAccessUser().getId() % 100;
                        String orderId = String.format("%1$ty%1$tm%1$td%2$02d%3$03d", now, remainderUserId, count);

                        return Long.valueOf(orderId);
                    } else if (count >= 100 && count < 10000) {
                        Long remainderUserId = AccessContext.getAccessUser().getId() % 10;
                        String orderId = String.format("%1$ty%1$tm%1$td%2$d%3$04d", now, remainderUserId, count);

                        return Long.valueOf(orderId);
                    } else {
                        String orderId = String.format("%1$ty%1$tm%1$td%2$d", now, count);

                        return Long.valueOf(orderId);
                    }
                }

            });
        };
    }

}
