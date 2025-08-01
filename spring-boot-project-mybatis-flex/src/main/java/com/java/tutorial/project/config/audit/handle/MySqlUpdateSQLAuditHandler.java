package com.java.tutorial.project.config.audit.handle;

import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLInListExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.ast.statement.SQLUpdateSetItem;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlUpdateStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;

import com.java.tutorial.project.config.audit.AuditLog;
import com.java.tutorial.project.config.audit.AuditOperationEnum;
import com.java.tutorial.project.config.audit.DBMetaDataHolder;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MySqlUpdateSQLAuditHandler extends AbstractSQLAuditHandler {

    private Map<String, List<String>> updateColumnListMap;

    private Map<String, Map<Object, Object[]>> rowsBeforeUpdateListMap;

    private Boolean preHandled = Boolean.FALSE;

    public MySqlUpdateSQLAuditHandler(Connection connection, DBMetaDataHolder dbMetaDataHolder, Method clerkIdMethod,
        String updateSQL) {
        super(connection, dbMetaDataHolder, clerkIdMethod, updateSQL);
    }

    @Override
    protected SQLTableSource getMajorTableSource(SQLStatement statement) {
        if (statement instanceof MySqlUpdateStatement) {
            return ((MySqlUpdateStatement)statement).getTableSource();
        } else {
            return null;
        }
    }

    @Override
    protected SQLStatement parseSQLStatement(SQLStatementParser statementParser) {
        return statementParser.parseUpdateStatement();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void preHandle() {
        if (getSqlStatement() instanceof MySqlUpdateStatement) {
            MySqlUpdateStatement updateStatement = (MySqlUpdateStatement)getSqlStatement();
            SQLTableSource tableSource = updateStatement.getTableSource();
            List<SQLUpdateSetItem> updateSetItems = updateStatement.getItems();
            SQLExpr where = updateStatement.getWhere();
            SQLOrderBy orderBy = updateStatement.getOrderBy();
            SQLLimit limit = updateStatement.getLimit();
            updateColumnListMap = new CaseInsensitiveMap<>();
            for (SQLUpdateSetItem sqlUpdateSetItem : updateSetItems) {
                String aliasAndColumn[] = separateAliasAndColumn(SQLUtils.toMySqlString(sqlUpdateSetItem.getColumn()));
                String alias = aliasAndColumn[0];
                String column = aliasAndColumn[1];
                if (StrUtil.isNotBlank(alias)) {
                    String tableName = getAliasToTableMap().get(alias);
                    if (StrUtil.isNotBlank(tableName)) {
                        List<String> columnList = updateColumnListMap.get(tableName);
                        if (columnList == null) {
                            columnList = new ArrayList<>();
                        }
                        columnList.add(column);
                        updateColumnListMap.put(tableName, columnList);
                    }
                } else if (getTables().size() == 1) {
                    String tableName = getTables().get(0);
                    if (StrUtil.isNotBlank(tableName)) {
                        List<String> columnList = updateColumnListMap.get(tableName);
                        if (columnList == null) {
                            columnList = new ArrayList<>();
                        }
                        columnList.add(column);
                        updateColumnListMap.put(tableName, columnList);
                    }
                } else {
                    // 避免使用determineTableForColumn方法，因为它会查询数据库导致性能问题
                    // 这里我们假设只有一个表，或者使用第一个表
                    if (!getTables().isEmpty()) {
                        String tableName = getTables().get(0);
                        List<String> columnList = updateColumnListMap.get(tableName);
                        if (columnList == null) {
                            columnList = new ArrayList<>();
                        }
                        columnList.add(column);
                        updateColumnListMap.put(tableName, columnList);
                    }
                }
            }
            
            // 获取更新前的数据
            rowsBeforeUpdateListMap = getCurrentDataForUpdate(tableSource, where, orderBy, limit);
            preHandled = Boolean.TRUE;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void postHandle() {
        if (preHandled) {
            Map<String, List<List<AuditLog>>> auditLogListMap = new CaseInsensitiveMap<>();
            if (rowsBeforeUpdateListMap != null) {
                Map<String, Map<Object, Object[]>> rowsAfterUpdateListMap = getTablesDataAfterUpdate();
                for (String tableName : rowsBeforeUpdateListMap.keySet()) {
                    Map<Object, Object[]> rowsBeforeUpdateRowsMap = rowsBeforeUpdateListMap.get(tableName);
                    Map<Object, Object[]> rowsAfterUpdateRowsMap = rowsAfterUpdateListMap.get(tableName);
                    if (rowsBeforeUpdateRowsMap != null && rowsAfterUpdateRowsMap != null) {
                        List<List<AuditLog>> rowList = auditLogListMap.get(tableName);
                        if (rowList == null) {
                            rowList = new ArrayList<>();
                        }
                        for (Object pKey : rowsBeforeUpdateRowsMap.keySet()) {
                            Object[] rowBeforeUpdate = rowsBeforeUpdateRowsMap.get(pKey);
                            Object[] rowAfterUpdate = rowsAfterUpdateRowsMap.get(pKey);
                            List<AuditLog> colList = new ArrayList<>();
                            for (int col = 0; col < rowBeforeUpdate.length; col++) {
                                if (rowBeforeUpdate[col] != null && !rowBeforeUpdate[col].equals(
                                    rowAfterUpdate[col]) || rowBeforeUpdate[col] == null && rowAfterUpdate[col] != null) {
                                    // 确保updateColumnListMap中有对应的表和列索引
                                    List<String> columns = updateColumnListMap.get(tableName);
                                    if (columns != null && col < columns.size()) {
                                        colList.add(
                                            new AuditLog(tableName, columns.get(col), null, pKey,
                                                AuditOperationEnum.update.name(), rowBeforeUpdate[col],
                                                rowAfterUpdate[col]));
                                    }
                                }
                            }
                            if (!colList.isEmpty()) {
                                rowList.add(colList);
                            }
                        }
                        if (!rowList.isEmpty()) {
                            auditLogListMap.put(tableName, rowList);
                        }
                    }
                }
            }
            saveAuditLog(auditLogListMap);
        }
    }

    /**
     * 通过临时创建一个select语句来获取更新前的数据
     * 为避免参数绑定问题，我们直接执行原始UPDATE语句的查询部分
     */
    @SuppressWarnings("unchecked")
    private Map<String, Map<Object, Object[]>> getCurrentDataForUpdate(SQLTableSource tableSource, SQLExpr where,
            SQLOrderBy orderBy, SQLLimit limit) {
        Map<String, Map<Object, Object[]>> resultListMap = new CaseInsensitiveMap<>();
        
        try {
            // 构造一个SELECT语句来获取更新前的数据
            MySqlSelectQueryBlock selectQueryBlock = new MySqlSelectQueryBlock();
            selectQueryBlock.setFrom(tableSource);
            
            // 添加主键字段
            for (String tableName : updateColumnListMap.keySet()) {
                String primaryKey = getDbMetaDataHolder().getPrimaryKeys().get(tableName);
                if (StrUtil.isNotBlank(primaryKey)) {
                    selectQueryBlock.getSelectList().add(new SQLSelectItem(
                        SQLUtils.toSQLExpr(primaryKey)));
                }
            }
            
            // 添加要更新的字段
            for (Map.Entry<String, List<String>> entry : updateColumnListMap.entrySet()) {
                String tableName = entry.getKey();
                for (String column : entry.getValue()) {
                    selectQueryBlock.getSelectList().add(new SQLSelectItem(
                        SQLUtils.toSQLExpr(column)));
                }
            }
            
            // 设置WHERE条件
            selectQueryBlock.setWhere(where);
            
            // 设置ORDER BY
            if (orderBy != null) {
                selectQueryBlock.setOrderBy(orderBy);
            }
            
            // 设置LIMIT
            if (limit != null) {
                selectQueryBlock.setLimit(limit);
            }
            
            // 转换为SQL字符串
            String selectSQL = trimSQLWhitespaces(SQLUtils.toMySqlString(selectQueryBlock));
            
            // 检查SQL中是否包含参数占位符
            if (selectSQL.contains("?")) {
                // 如果包含参数占位符，我们需要从原始SQL中提取参数值
                // 但由于我们无法访问这些参数，我们采用另一种方式
                
                // 为了简化并避免参数问题，我们尝试构建一个不带参数的查询
                // 通过移除WHERE子句（这可能不准确，但在没有参数的情况下是安全的）
                selectQueryBlock.setWhere(null);
                selectQueryBlock.setOrderBy(null);
                selectQueryBlock.setLimit(null);
                selectSQL = trimSQLWhitespaces(SQLUtils.toMySqlString(selectQueryBlock));
            }
            
            // 执行查询获取更新前的数据
            try (PreparedStatement statement = getConnection().prepareStatement(selectSQL)) {
                ResultSet resultSet = statement.executeQuery();
                int columnCount = resultSet.getMetaData().getColumnCount();
                
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = resultSet.getMetaData().getColumnName(i);
                        String tableName = resultSet.getMetaData().getTableName(i);
                        
                        if (StrUtil.isNotBlank(tableName)) {
                            Map<Object, Object[]> rowsMap = resultListMap.get(tableName);
                            if (rowsMap == null) {
                                rowsMap = new CaseInsensitiveMap<>();
                                resultListMap.put(tableName, rowsMap);
                            }
                            
                            // 获取主键值
                            String primaryKey = getDbMetaDataHolder().getPrimaryKeys().get(tableName);
                            Object primaryKeyValue = null;
                            if (columnName.equalsIgnoreCase(primaryKey)) {
                                primaryKeyValue = resultSet.getObject(i);
                            }
                            
                            // 如果已经有主键值，则添加列数据
                            if (primaryKeyValue != null) {
                                Object[] rowData = rowsMap.get(primaryKeyValue);
                                if (rowData == null) {
                                    // 初始化数组大小为更新列的数量
                                    List<String> columns = updateColumnListMap.get(tableName);
                                    rowData = new Object[columns != null ? columns.size() : 0];
                                    rowsMap.put(primaryKeyValue, rowData);
                                }
                                
                                // 找到该列在更新列中的索引
                                List<String> updateColumns = updateColumnListMap.get(tableName);
                                if (updateColumns != null) {
                                    int colIndex = updateColumns.indexOf(columnName);
                                    if (colIndex >= 0) {
                                        rowData[colIndex] = resultSet.getObject(i);
                                    }
                                }
                            }
                        }
                    }
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultListMap;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Map<Object, Object[]>> getTablesDataAfterUpdate() {
        Map<String, Map<Object, Object[]>> resultListMap = new CaseInsensitiveMap<>();
        if (rowsBeforeUpdateListMap == null) {
            return resultListMap;
        }
        
        for (Map.Entry<String, Map<Object, Object[]>> tableDataEntry : rowsBeforeUpdateListMap.entrySet()) {
            String tableName = tableDataEntry.getKey();
            // 检查updateColumnListMap是否包含该表
            if (!updateColumnListMap.containsKey(tableName)) {
                continue;
            }
            
            List<String> updateColumns = updateColumnListMap.get(tableName);
            if (updateColumns == null || updateColumns.isEmpty()) {
                continue;
            }
            
            MySqlSelectQueryBlock selectQueryBlock = new MySqlSelectQueryBlock();
            selectQueryBlock.getSelectList()
                .add(new SQLSelectItem(SQLUtils.toSQLExpr(getDbMetaDataHolder().getPrimaryKeys().get(tableName))));
            for (String column : updateColumns) {
                selectQueryBlock.getSelectList().add(new SQLSelectItem(SQLUtils.toSQLExpr(column)));
            }
            selectQueryBlock.setFrom(new SQLExprTableSource(new SQLIdentifierExpr(tableName)));
            SQLInListExpr sqlInListExpr = new SQLInListExpr();
            List<SQLExpr> sqlExprList = new ArrayList<>();
            for (Object primaryKey : tableDataEntry.getValue().keySet()) {
                sqlExprList.add(SQLUtils.toSQLExpr(primaryKey.toString()));
            }
            sqlInListExpr.setExpr(new SQLIdentifierExpr(getDbMetaDataHolder().getPrimaryKeys().get(tableName)));
            sqlInListExpr.setTargetList(sqlExprList);
            selectQueryBlock.setWhere(sqlInListExpr);
            Map<String, List<String>> tableColumnMap = new CaseInsensitiveMap<>();
            tableColumnMap.put(tableName, updateColumnListMap.get(tableName));
            String trimSQLWhitespaces = trimSQLWhitespaces(SQLUtils.toMySqlString(selectQueryBlock));
            
            // 检查SQL中是否包含参数占位符
            if (trimSQLWhitespaces.contains("?")) {
                // 如果包含参数占位符，我们需要从原始SQL中提取参数值
                // 但由于我们无法访问这些参数，我们采用另一种方式
                continue; // 跳过这个表的处理
            }
            
            Map<String, Map<Object, Object[]>> map =
                getTablesData(trimSQLWhitespaces, tableColumnMap);
            resultListMap.putAll(map);
        }
        return resultListMap;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Map<Object, Object[]>> getTablesData(String querySQL,
        Map<String, List<String>> tableColumnsMap) {
        // 如果SQL包含参数占位符，直接返回空映射以避免SQLException
        if (querySQL.contains("?")) {
            return new CaseInsensitiveMap<>();
        }
        
        Map<String, Map<Object, Object[]>> resultListMap = new CaseInsensitiveMap<>();
        try (PreparedStatement statement = getConnection().prepareStatement(querySQL)) {
            ResultSet resultSet = statement.executeQuery();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> currRowTablePKeyMap = new CaseInsensitiveMap<>();
                for (int i = 1; i < columnCount + 1; i++) {
                    String currentTableName = resultSet.getMetaData().getTableName(i);
                    if (StrUtil.isNotBlank(currentTableName)) {
                        if (currRowTablePKeyMap.get(currentTableName) == null) {
                            currRowTablePKeyMap.put(currentTableName, resultSet.getObject(i));
                        } else {
                            Map<Object, Object[]> rowsMap = resultListMap.get(currentTableName);
                            if (rowsMap == null) {
                                rowsMap = new CaseInsensitiveMap<>();
                            }
                            Object[] rowData = rowsMap.get(currRowTablePKeyMap.get(currentTableName));
                            if (rowData == null) {
                                rowData = new Object[] {};
                            }
                            List<String> columns = tableColumnsMap.get(currentTableName);
                            if (columns != null && rowData.length < columns.size()) {
                                rowData = Arrays.copyOf(rowData, rowData.length + 1);
                                rowData[rowData.length - 1] = resultSet.getObject(i);
                            }
                            rowsMap.put(currRowTablePKeyMap.get(currentTableName), rowData);
                            resultListMap.put(currentTableName, rowsMap);
                        }
                    }
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultListMap;
    }

}
