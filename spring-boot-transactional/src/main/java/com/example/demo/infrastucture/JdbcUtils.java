package com.example.demo.infrastucture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * @author HY
 */
@Slf4j
@Component
public class JdbcUtils {


    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;

    @Value("${spring.datasource.url}")
    private String masterDataUrlAddress;

    @Value("${spring.datasource.username}")
    private String getMasterDataUsername;

    @Value("${spring.datasource.password}")
    private String getMasterDataPassword;


    /**
     *
     * @param dataList
     * @param typeSortList
     * @param insertSql
     */
    public void batchGenerateMysqlUtil(List<Map<String, Object>> dataList, List<String> typeSortList, String insertSql) {
        PreparedStatement stat = null;
        Connection conn = null;
        if (dataList == null) {
            return;
        }
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(masterDataUrlAddress, getMasterDataUsername, getMasterDataPassword);
            conn.setAutoCommit(false);
            stat = conn.prepareStatement(insertSql);
            for (Map<String, Object> valueBean : dataList) {
                int i = 1;
                for (String key : typeSortList) {
                    String val = "";
                    if (valueBean.get(key) != null) {
                        val = valueBean.get(key).toString();
                    }
                    stat.setString(i, val);
                    i++;
                }
                stat.addBatch();
            }
            stat.executeBatch();
            conn.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException ex) {
                log.error(ex.getMessage());
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                    conn.commit();
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

}
