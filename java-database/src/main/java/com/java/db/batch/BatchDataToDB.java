package com.java.db.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @author HY
 */
public class BatchDataToDB {

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private String DATA_BASE_PATH =
        "jdbc:mysql://localhost:3366/mag_1223_test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&allowMultiQueries=true";

    private String SPACE_STRING = " ";

    /**
     * 批处理存入数据
     *
     * @param dataList     数据
     * @param typeSortList 数据对应的列名
     * @param insertSql    执行的SQL
     */
    public void saveTransToSqliteTable(List<Map<String, Object>> dataList, List<String> typeSortList,
        String insertSql) {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DATA_BASE_PATH);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement stat = null;
        try {
            assert conn != null;
            conn.setAutoCommit(false);
            stat = conn.prepareStatement(insertSql);
            for (Map<String, Object> valueBean : dataList) {
                int i = 1;
                for (String key : typeSortList) {
                    String val = SPACE_STRING;
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除DB表所有数据
     *
     * @param deleteSql sql
     */
    public void deleteTransToTable(String deleteSql) {
        Statement stmt = null;
        try {
            //加载数据库驱动
            Class.forName(JDBC_DRIVER);
            //建立数据库连接，获得连接对象conn
            Connection conn = DriverManager.getConnection(DATA_BASE_PATH);
            stmt = conn.createStatement();
            stmt.executeUpdate(deleteSql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
