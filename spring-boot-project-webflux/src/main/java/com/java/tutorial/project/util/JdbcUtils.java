package com.java.tutorial.project.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.db.DbUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.StringUtils;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JdbcUtils {

    public static Map<String, Object> getResultMap(ResultSet resultSet) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int count = resultSetMetaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            String key = resultSetMetaData.getColumnLabel(i);
            Object value = resultSet.getObject(i);
            map.put(key, value);
        }
        return map;
    }

    public static void batchInsertLarge(String dbTable, List<Map<String, Object>> mapEntities, JdbcTemplate jt) {
        if (CollUtil.isEmpty(mapEntities)) {
            return;
        }
        Tuple2<String, List<String>> tuple2 = mapToInsertSql(dbTable, mapEntities.get(0));
        String sql = tuple2.getT1();
        List<String> columns = tuple2.getT2();
        //JdbcTemplate jt = SpringUtil.getBean("targetJdbcTemplate");
        DataSource dataSource = jt.getDataSource();
        Connection con = null;
        PreparedStatement pst = null;
        try {
            //con = DataSourceUtils.getConnection(dataSource);
            //con = DbUtils.getConnection1();
            con = dataSource.getConnection();

            con.setAutoCommit(false);
            // todo 开启事务
            pst = con.prepareStatement(sql);
            int i = 0;
            int n = mapEntities.size();
            for (Map<String, Object> mapEntity : mapEntities) { // 一次循环处理一行数据
                // 每行数据字段列表值 ？？？填充
                Object[] values = mapToParams(columns, mapEntity);
                int colIndex = 0;
                for (Object value : values) { // ？？？填充
                    colIndex++;
                    pst.setObject(colIndex, value);
                }
                pst.addBatch();
                if (i % 5000 == 0 || i == (n - 1)) { // 5000 3. 每5000条插入一次 **********************************************
                    pst.executeBatch();
                    con.commit();
                    pst.clearBatch();
                }
                i++;
            }
        } catch (Exception e) {
            log.error("批量插入数据出错", e);
            try {
                con.rollback();
            } catch (SQLException ex) {
                log.error("回滚数据出错", ex);
            }
            log.error("执行批量插入方法出错，记录数：{}", mapEntities.size());
            // throw ProblemSolver.server(ProblemCode.DATABASE_LINK_ERROR).withDetail(e.getMessage()).build();
        } finally {
            DbUtil.close(pst);
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }

    private static Tuple2<String, List<String>> mapToInsertSql(String dbTable, Map<String, Object> entity) {
        return mapToInsertSql("INSERT INTO ", dbTable, entity, 0);
    }

    static Tuple2<String, List<String>> mapToInsertSql(String head, String dbTable, Map<String, Object> entity,
        int size) {
        StringBuilder columnBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();
        List<String> columns = new ArrayList<>();
        for (Map.Entry<String, Object> entry : entity.entrySet()) {
            String key = entry.getKey();
            if (!StringUtils.isEmpty(key)) {
                if (columnBuilder.length() > 0) {
                    columnBuilder.append(",");
                }
                if (valueBuilder.length() > 0) {
                    valueBuilder.append(",");
                }
                columnBuilder.append(key);
                valueBuilder.append("?");
                columns.add(key);
            }
        }
        String sql;
        if (size < 1) {
            sql = head + dbTable + " ( " + columnBuilder + " ) " + "VALUES" + " ( " + valueBuilder + " ) ";
        } else {
            String value = " ( " + valueBuilder + " ) ";
            StringBuilder valuesBuilder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                if (valuesBuilder.length() > 0) {
                    valuesBuilder.append(", ");
                }
                valuesBuilder.append(value);
            }
            sql = head + dbTable + " ( " + columnBuilder + " ) " + "VALUES" + valuesBuilder;
        }
        return Tuples.of(sql, columns);
    }

    private static Object[] mapToParams(List<String> columns, Map<String, Object> entity) {
        List<Object> args = new ArrayList<>();
        for (String column : columns) {
            args.add(entity.get(column));
        }
        return args.toArray();
    }

}

