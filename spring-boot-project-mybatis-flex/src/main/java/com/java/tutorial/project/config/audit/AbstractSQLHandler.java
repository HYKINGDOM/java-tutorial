package com.java.tutorial.project.config.audit;

import cn.hutool.core.map.CaseInsensitiveMap;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import lombok.Getter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public abstract class AbstractSQLHandler implements ISQLHandler {

    private final Connection connection;

    private final String sql;

    private List<String> tables;

    private Map<String, String> aliasToTableMap;

    private Map<String, String> tableToAliasMap;

    private SQLStatement sqlStatement;

    private static final Pattern pattern1 = Pattern.compile("(([A-Za-z0-9_]+)\\.)?`?([A-Za-z0-9_]+)`?");

    private static final Pattern pattern2 = Pattern.compile("`\\.`");

    private static final Pattern pattern3 = Pattern.compile("[\\s]+");

    protected abstract SQLTableSource getMajorTableSource(SQLStatement statement);

    protected abstract SQLStatement parseSQLStatement(SQLStatementParser statementParser);

    public AbstractSQLHandler(Connection connection, String sql) {
        this.connection = connection;
        this.sql = sql;
        init();
    }

    private void init() {
        sqlStatement = parseSQLStatement(new MySqlStatementParser(sql));
        SQLTableSource sqlTableSource = getMajorTableSource(sqlStatement);
        if (sqlTableSource != null) {
            aliasToTableMap = buildAliasToTableMap(sqlTableSource);
            tableToAliasMap = reverseKeyAndValueOfMap(aliasToTableMap);
            tables = new ArrayList<>(tableToAliasMap.keySet());
        }
    }

    public static String trimSQLWhitespaces(String sql) {
        return pattern3.matcher(sql).replaceAll(" ");
    }

    private static String normalize(String name) {
        if (name == null) {
            return null;
        }

        if (name.length() > 2) {
            char c0 = name.charAt(0);
            char x0 = name.charAt(name.length() - 1);
            if ((c0 == '"' && x0 == '"') || (c0 == '`' && x0 == '`')) {
                String normalizeName = name.substring(1, name.length() - 1);
                if (c0 == '`') {
                    normalizeName = pattern2.matcher(normalizeName).replaceAll(".");
                }
                return normalizeName;
            }
        }

        return name;
    }

    public static String[] separateAliasAndColumn(String combinedColumn) {
        String alias = null;
        String column = null;
        Matcher matcher = pattern1.matcher(combinedColumn);
        if (matcher.matches()) {
            switch (matcher.groupCount()) {
                case 3:
                    alias = matcher.group(2);
                    column = matcher.group(3);
                    break;
                case 1:
                    column = matcher.group(1);
                    break;
                default:
                    break;
            }
        }
        return new String[] {alias, column};
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> reverseKeyAndValueOfMap(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        Map<String, String> resultMap = new CaseInsensitiveMap<>();
        for (String key : keySet) {
            String value = map.get(key);
            resultMap.put(value, key);
        }
        return resultMap;
    }

    public final String determineTableForColumn(String column) {
        try {
            for (String table : tables) {
                ResultSet resultSet = getConnection().getMetaData()
                    .getColumns(null, getConnection().getMetaData().getUserName(), table, column);
                if (resultSet.next()) {
                    resultSet.close();
                    return table;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> buildAliasToTableMap(SQLTableSource tableSource) {
        Map<String, String> map = new CaseInsensitiveMap<>();
        if (tableSource != null && SQLJoinTableSource.class == tableSource.getClass()) {
            map.putAll(buildAliasToTableMap(((SQLJoinTableSource)tableSource).getLeft()));
            map.putAll(buildAliasToTableMap(((SQLJoinTableSource)tableSource).getRight()));
        } else if (tableSource != null && SQLExprTableSource.class == tableSource.getClass()) {
            SQLExprTableSource exprTableSource = (SQLExprTableSource)tableSource;
            String alias = exprTableSource.getAlias();
            if (alias == null) {
                if (exprTableSource.getExpr() instanceof SQLName) {
                    alias = ((SQLName)exprTableSource.getExpr()).getSimpleName();
                }
            }
            map.put(normalize(alias), ((SQLName)exprTableSource.getExpr()).getSimpleName());
        }
        return map;
    }
}