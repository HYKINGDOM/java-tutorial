package com.java.tutorial.project.config.audit.handle;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;

import com.java.tutorial.project.config.audit.AuditLog;
import com.java.tutorial.project.config.audit.AuditOperationEnum;
import com.java.tutorial.project.config.audit.DBMetaDataHolder;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlInsertSQLAuditHandler extends AbstractSQLAuditHandler {

    private String table;

    private final List<String> columnList = new ArrayList<>();

    private Boolean preHandled = Boolean.FALSE;

    public MySqlInsertSQLAuditHandler(Connection connection, DBMetaDataHolder dbMetaDataHolder, Method clerkIdMethod,
        String insertSQL) {
        super(connection, dbMetaDataHolder, clerkIdMethod, insertSQL);
    }

    @Override
    protected SQLStatement parseSQLStatement(SQLStatementParser statementParser) {
        return statementParser.parseInsert();
    }

    @Override
    protected SQLTableSource getMajorTableSource(SQLStatement statement) {
        if (statement instanceof MySqlInsertStatement) {
            return ((MySqlInsertStatement)statement).getTableSource();
        } else {
            return null;
        }
    }

    @Override
    public void preHandle() {
        if (getSqlStatement() instanceof MySqlInsertStatement) {
            MySqlInsertStatement sqlInsertStatement = (MySqlInsertStatement)getSqlStatement();
            if (!sqlInsertStatement.getColumns().isEmpty()) {
                SQLExpr sqlExpr = sqlInsertStatement.getColumns().get(0);
                String[] aliasAndColumn = separateAliasAndColumn(SQLUtils.toMySqlString(sqlExpr));
                if (aliasAndColumn[0] != null) {
                    table = getAliasToTableMap().get(aliasAndColumn[0]);
                } else if (getTables().size() == 1) {
                    table = getTables().get(0);
                } else {
                    table = determineTableForColumn(aliasAndColumn[1]);
                }
                for (int i = 0; i < sqlInsertStatement.getColumns().size(); i++) {
                    SQLExpr columnExpr = sqlInsertStatement.getColumns().get(i);
                    columnList.add(separateAliasAndColumn(SQLUtils.toMySqlString(columnExpr))[1]);
                }
            }
            preHandled = Boolean.TRUE;
        }
    }

    @Override
    public void postHandle() {
        if (preHandled) {
            List<List<AuditLog>> auditLogs = new ArrayList<>();
            try (Statement statement = getConnection().createStatement()) {
                ResultSet limitResultSet = statement.executeQuery(
                    "SELECT rowno - 1, rowcon FROM (SELECT @rowno := @rowno + 1 AS rowno, t2.rowcon AS rowcon, ID FROM " + table + " r, (SELECT @rowno := 0) t, (SELECT ROW_COUNT() AS rowcon) t2 order by r.id asc) b WHERE b.ID = (SELECT LAST_INSERT_ID())");
                if (limitResultSet.next()) {
                    Integer limit_1 = limitResultSet.getInt(1);
                    Integer limit_2 = limitResultSet.getInt(2);
                    StringBuilder sb = new StringBuilder();
                    sb.append(getDbMetaDataHolder().getPrimaryKeys().get(table));
                    for (String column : columnList) {
                        sb.append(", ");
                        sb.append(column);
                    }
                    ResultSet resultSet = statement.executeQuery(String.format(
                        "select %s from %s where id>=(select id from %s order by id asc limit %s,1) limit %s",
                        sb.toString(), table, table, limit_1, limit_2));
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    while (resultSet.next()) {
                        List<AuditLog> list = new ArrayList<>();
                        Object primaryKey = null;
                        for (int i = 1; i < columnCount + 1; i++) {
                            if (i == 1) {
                                primaryKey = resultSet.getObject(i);
                            } else {
                                AuditLog auditLog = new AuditLog(table, columnList.get(i - 2), null, primaryKey,
                                    AuditOperationEnum.insert.name(), null, resultSet.getObject(i));
                                list.add(auditLog);
                            }
                        }
                        auditLogs.add(list);
                    }
                    resultSet.close();
                }
                limitResultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            saveAuditLog(auditLogs);
        }
    }

}
