//package com.java.util.javautil.utils;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.sql.*;
//import java.util.List;
//import java.util.Map;
//
///**
// * JDBC SQL批处理工具类
// *
// * @author Administrator
// */
//@Component
//public class JdbcBatchUtils {
//
//    @Value("${spring.datasource.driverClassName}")
//    private String jdbcDriver;
//
//    @Value("${spring.datasource.druid.master.url}")
//    private String masterDataUrlAddress;
//
//    @Value("${spring.datasource.druid.master.username}")
//    private String getMasterDataUsername;
//
//    @Value("${spring.datasource.druid.master.password}")
//    private String getMasterDataPassword;
//
//    /**
//     * 批处理SQL
//     *
//     * @param dataList
//     * @param typeSortList
//     * @param insertSql
//     */
//    public void batchGenerateMysqlUtil(List<Map<String, Object>> dataList, List<String> typeSortList, String insertSql) {
//        PreparedStatement stat = null;
//        Connection conn = null;
//        if (dataList == null) {
//            return;
//        }
//        try {
//            Class.forName(jdbcDriver);
//            conn = DriverManager.getConnection(masterDataUrlAddress, getMasterDataUsername, getMasterDataPassword);
//            conn.setAutoCommit(false);
//            stat = conn.prepareStatement(insertSql);
//            for (Map<String, Object> valueBean : dataList) {
//                int i = 1;
//                for (String key : typeSortList) {
//                    String val = "";
//                    if (valueBean.get(key) != null) {
//                        val = valueBean.get(key).toString();
//                    }
//                    stat.setString(i, val);
//                    i++;
//                }
//                stat.addBatch();
//            }
//            stat.executeBatch();
//            conn.commit();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.getMessage();
//        } finally {
//            try {
//                if (stat != null) {
//                    stat.close();
//                    conn.commit();
//                }
//            } catch (SQLException e) {
//                e.getMessage();
//            }
//        }
//    }
//
//
//    /**
//     * 执行特定语句的批处理
//     *
//     * @param sqlStatement
//     */
//    public void batchSpeciallySqlMysqlTable(String sqlStatement) {
//        Statement stmt = null;
//        Connection conn = null;
//        try {
//            Class.forName(jdbcDriver);
//            conn = DriverManager.getConnection(masterDataUrlAddress, getMasterDataUsername, getMasterDataPassword);
//            stmt = conn.createStatement();
//            stmt.executeUpdate(sqlStatement);
//            conn.commit();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.getMessage();
//        } finally {
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                    conn.commit();
//                }
//            } catch (SQLException e) {
//                e.getMessage();
//            }
//        }
//    }
//
//
//    /**
//     * 获取连接
//     *
//     * @return 连接对象
//     */
//    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(masterDataUrlAddress, getMasterDataUsername, getMasterDataPassword);
//    }
//
//    /**
//     * 释放资源
//     *
//     * @param rs
//     * @param st
//     * @param conn
//     */
//    public void close(ResultSet rs, Statement st, Connection conn) {
//        if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (st != null) {
//            try {
//                st.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
//
