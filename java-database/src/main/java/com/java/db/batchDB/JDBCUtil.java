package com.java.db.batchDB;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 单连接 JDBC工具类 重复创建连接会关闭原来的连接
 *
 * @author hy
 */
public abstract class JDBCUtil {
    private static final String driverClass = "com.mysql.jdbc.Driver";

    // 静态代码块 初始化驱动
    static {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 连接状态  0 未连接 , 1 已连接 , 2 覆盖连接 , 3 重连 , 4 关闭中 , 5 连接中
    public enum ConnectionStatus {
        CLOSE(0), CONNECTIONED(1), OVERRIDE(2), RECONNECTING(3), CLOSING(4), CONNECTING(5);

        private int status;

        public int getStatus() {
            return status;
        }

        ConnectionStatus(int status) {
            this.status = status;
        }
    }

    public enum SqlType {
        SELECT(1), UPDATE(2), INSERT(3), DELETE(4);
        private int type;

        public int getType() {
            return type;
        }

        SqlType(int type) {
            this.type = type;
        }
    }

    // 活跃连接
    public static Connection connection;

    // 连接状态  0 未连接 , 1 已连接 , 2 覆盖连接 , 3 重连 , 4 关闭中 , 5 连接中
    public static int connectionStatus = ConnectionStatus.CLOSE.status;

    private static String host;
    private static String port;
    private static String url;
    private static String database;
    private static String username;
    private static String password;

    /**
     * 创建连接
     *
     * @param host     主机地址
     * @param port     端口
     * @param database 数据库
     * @param username 用户名
     * @param password 密码
     * @return 返回JDBC连接
     * @throws SQLException
     */
    public static Connection createConnection(String host, String port, String database, String username,
        String password) throws SQLException {
        JDBCUtil.host = host;
        JDBCUtil.port = port;
        JDBCUtil.database = database;
        JDBCUtil.username = username;
        JDBCUtil.password = password;

        boolean isOverride = false;
        if (connection != null) {
            isOverride = true;
            connectionStatus = ConnectionStatus.CONNECTING.status;
            connection.close();
            connection = null;
            connectionStatus = ConnectionStatus.CLOSE.status;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(host);
        stringBuilder.append(":");
        stringBuilder.append(port);
        stringBuilder.append("/");
        stringBuilder.append(database);

        Connection conn = createConnection(stringBuilder.toString(), username, password);
        connection = conn;
        if (isOverride) {
            connectionStatus = ConnectionStatus.OVERRIDE.status;
        } else {
            connectionStatus = ConnectionStatus.CONNECTIONED.status;
        }
        return conn;
    }

    /**
     * 创建连接
     *
     * @param url      连接地址
     * @param username 用户名
     * @param password 密码
     * @return 返回 JDBC连接
     * @throws SQLException
     */
    public static Connection createConnection(String url, String username, String password) throws SQLException {
        JDBCUtil.url = url;
        JDBCUtil.username = username;

        boolean isOverride = false;
        if (connection != null) {
            isOverride = true;
            connectionStatus = ConnectionStatus.CONNECTING.status;
            connection.close();
            connection = null;
            connectionStatus = ConnectionStatus.CLOSE.status;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("jdbc:mysql://");
        stringBuilder.append(url);
        Connection conn = DriverManager.getConnection(stringBuilder.toString(), username, password);
        connection = conn;

        if (isOverride) {
            connectionStatus = ConnectionStatus.OVERRIDE.status;
        } else {
            connectionStatus = ConnectionStatus.CONNECTIONED.status;
        }
        return conn;
    }

    /**
     * 重连
     *
     * @return 返回连接
     * @throws SQLException
     */
    public static Connection reconnect() throws SQLException {
        if (url != null) {
            connectionStatus = ConnectionStatus.RECONNECTING.status;
            return createConnection(url, username, password);
        } else if (host != null && port != null && database != null) {
            connectionStatus = ConnectionStatus.RECONNECTING.status;
            return createConnection(host, port, database, username, password);
        }
        return null;
    }

    /**
     * 获取 statement
     *
     * @return statement
     * @throws SQLException
     */
    public static Statement createStatement() throws SQLException {
        if (connection == null) {
            reconnect();
        }
        Statement statement = connection.createStatement();
        return statement;
    }

    /**
     * 获取statement
     *
     * @param sql 预编译sql
     * @return statement
     * @throws SQLException
     */
    public static PreparedStatement createPreparedStatement(String sql) throws SQLException {
        if (connection == null) {
            reconnect();
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement;
    }

    /**
     * 查询数据
     *
     * @param wherePojo  where 语句 POJO
     * @param returnType 返回 POJO class
     * @throws SQLException
     */
    public static <W, T> T selectOne(W wherePojo, Class<T> returnType) throws SQLException {

        // 参数列表集合 支持 LinkedList ArrayList
        List<Object> paramList = new LinkedList<>();

        String sql = parseSql(SqlType.SELECT, null, wherePojo, paramList);
        if (sql != null && !sql.contains("limit")) {
            sql += " limit 1";
        }

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T ret = null;
        try {
            resultSet = doSelect(sql, statement, paramList.toArray());
            // 处理结果集 反射生成pojo
            ret = parseResultSet(resultSet, returnType);
        } catch (SQLException e) {
            throw e;
        } finally {
            // 关闭资源
            close(statement, resultSet);
        }

        return ret;
    }

    /**
     * 查询数据
     *
     * @param wherePojo  where 语句 POJO
     * @param returnType 返回 POJO class
     * @throws SQLException
     */
    public static <W, L, T> Collection<T> selectAll(W wherePojo, Class<L> listType, Class<T> returnType)
        throws SQLException {

        // 参数列表集合 支持 LinkedList ArrayList
        List<Object> paramList = new LinkedList<>();

        String sql = parseSql(SqlType.SELECT, null, wherePojo, paramList);

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Collection<T> ret = null;
        try {
            resultSet = doSelect(sql, statement, paramList.toArray());
            // 处理结果集 反射生成pojo
            ret = parseResultSet(resultSet, listType, returnType);
        } catch (SQLException e) {
            throw e;
        } finally {
            // 关闭资源
            close(statement, resultSet);
        }

        return ret;
    }

    /**
     * 插入数据
     *
     * @param pojo POJO
     * @throws SQLException
     */
    public static <T> void insert(T pojo) throws SQLException {

        // 参数列表集合 支持 LinkedList ArrayList
        List<Object> paramList = new LinkedList<>();

        String sql = parseSql(SqlType.INSERT, pojo, null, paramList);
        doUpdate(sql, paramList.toArray());
    }

    /**
     * 修改数据
     *
     * @param pojo      POJO
     * @param wherePojo where POJO
     * @throws SQLException
     */
    public static <T, W> void update(T pojo, W wherePojo) throws SQLException {

        // 参数列表集合 支持 LinkedList ArrayList
        List<Object> paramList = new LinkedList<>();

        String sql = parseSql(SqlType.UPDATE, pojo, wherePojo, paramList);
        doUpdate(sql, paramList.toArray());
    }

    /**
     * 删除数据
     *
     * @param wherePojo where POJO
     * @throws SQLException
     */
    public static <T, W> void delete(W wherePojo) throws SQLException {

        // 参数列表集合 支持 LinkedList ArrayList
        List<Object> paramList = new LinkedList<>();

        String sql = parseSql(SqlType.DELETE, null, wherePojo, paramList);
        doUpdate(sql, paramList.toArray());
    }

    /**
     * 查询一条数据 自动加上 limit 1
     *
     * @param sql        查询语句
     * @param returnType 返回类型
     * @throws SQLException
     */
    public static <T> T selectOne(String sql, Class<T> returnType) throws SQLException {
        return selectOne(sql, returnType, new Object[0]);
    }

    /**
     * 查询一条数据 自动加上 limit 1
     *
     * @param sql        查询语句
     * @param returnType 返回类型
     * @param params     查询参数
     * @throws SQLException
     */
    public static <T> T selectOne(String sql, Class<T> returnType, Object... params) throws SQLException {
        if (connection == null) {
            reconnect();
        }
        if (sql == null) {
            return null;
        }
        if (!sql.contains("limit")) {
            sql += " limit 1";
        }

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T ret = null;
        try {
            resultSet = doSelect(sql, statement, params);
            // 处理结果集 反射生成pojo
            ret = parseResultSet(resultSet, returnType);
        } catch (SQLException e) {
            throw e;
        } finally {
            // 关闭资源
            close(statement, resultSet);
        }

        return ret;
    }

    /**
     * 查询列表数据
     *
     * @param sql             查询语句
     * @param collectionClass 列表类型  默认为 ArrayList  可以为 List 或者 Set 中可实例化的类 不要使用接口和抽象类 implements Collection
     * @param returnType      返回类型
     * @throws SQLException
     */
    public static <T, L> Collection<T> selectAll(String sql, Class<L> collectionClass, Class<T> returnType)
        throws SQLException {
        return selectAll(sql, collectionClass, returnType, new Object[0]);
    }

    /**
     * 查询列表数据
     *
     * @param sql             查询语句
     * @param collectionClass 列表类型  默认为 ArrayList  可以为 List 或者 Set 中可实例化的类 不要使用接口和抽象类 implements Collection
     * @param returnType      返回类型
     * @param params          查询参数
     * @throws SQLException
     */
    public static <T, L> Collection<T> selectAll(String sql, Class<L> collectionClass, Class<T> returnType,
        Object... params) throws SQLException {
        if (connection == null) {
            reconnect();
        }
        if (sql == null) {
            return null;
        }

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Collection<T> ret = null;
        try {
            resultSet = doSelect(sql, statement, params);
            // 处理结果集 反射生成pojo
            ret = parseResultSet(resultSet, collectionClass, returnType);
        } catch (SQLException e) {
            throw e;
        } finally {
            // 关闭资源
            close(statement, resultSet);
        }
        return ret;
    }

    /**
     * 插入数据
     *
     * @param sql    插入语句
     * @param params 参数列表
     * @throws SQLException
     */
    public static void insert(String sql, Object... params) throws SQLException {
        doUpdate(sql, params);
    }

    /**
     * 更新数据
     *
     * @param sql    更新语句
     * @param params 参数列表
     * @throws SQLException
     */
    public static void update(String sql, Object... params) throws SQLException {
        doUpdate(sql, params);
    }

    /**
     * 删除数据
     *
     * @param sql    删除语句
     * @param params 参数列表
     * @throws SQLException
     */
    public static void delete(String sql, Object... params) throws SQLException {
        doUpdate(sql, params);
    }

    /**
     * 执行 select
     *
     * @param sql    查询语句
     * @param params 查询参数
     * @return 结果集
     * @throws SQLException
     */
    public static ResultSet doSelect(String sql, PreparedStatement statement, Object... params) throws SQLException {
        ResultSet resultSet = null;

        // 预编译sql语句
        statement = createPreparedStatement(sql);
        if (statement == null) {
            return null;
        }
        // 插入查询参数
        parsePreparedStatement(statement, params);

        // 执行查询 返回结果集
        resultSet = statement.executeQuery();
        if (resultSet == null) {
            return null;
        } else {
            return resultSet;
        }
    }

    /**
     * 插入/修改/删除数据
     *
     * @param sql    修改语句
     * @param params 查询参数
     * @throws SQLException
     */
    public static void doUpdate(String sql, Object... params) throws SQLException {
        if (connection == null) {
            reconnect();
        }
        if (sql == null) {
            return;
        }

        PreparedStatement statement = null;

        try {
            // 预编译sql语句
            statement = createPreparedStatement(sql);
            // 插入参数
            parsePreparedStatement(statement, params);

            // 执行更新
            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            // 关闭资源
            close(statement);
        }
    }

    /**
     * 生成sql语句
     *
     * @param sqlType   SELECT UPDATE INSERT DELETE
     * @param pojo      set POJO
     * @param wherePojo where POJO
     * @param paramList 参数列表
     * @return
     */
    public static <T, W> String parseSql(SqlType sqlType, T pojo, W wherePojo, List paramList) {
        StringBuilder sql = new StringBuilder();
        // 插入 修改 使用set
        // insert user set name="colagy"
        // update user set name="colagy" where name="coalgy"
        if (sqlType == SqlType.INSERT || sqlType == SqlType.UPDATE) {
            if (sqlType == SqlType.INSERT) {
                sql.append("insert");
            } else if (sqlType == SqlType.UPDATE) {
                sql.append("update");
            }
            sql.append(" ");

            Class<?> pc = pojo.getClass();

            String tableName = parseTableName(pc);
            sql.append("`");
            sql.append(tableName);
            sql.append("`");
            sql.append(" ");
            sql.append("set");
            sql.append(" ");

            Field[] fields = pc.getDeclaredFields();
            List<String> pl = new LinkedList<>();

            // 处理 set
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                Object value = null;
                try {
                    value = field.get(pojo);
                    if (value == null) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(key);
                    sb.append("=?");
                    pl.add(sb.toString());
                    paramList.add(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < pl.size(); i++) {
                sql.append(pl.get(i));
                if (i != pl.size() - 1) {
                    sql.append(",");
                }
            }
        } else if (sqlType == SqlType.DELETE) {
            // delete from user where name="colagy"
            // 删除使用 delete from
            sql.append("delete from ");
            Class<?> wc = wherePojo.getClass();

            String tableName = parseTableName(wc);
            sql.append("`");
            sql.append(tableName);
            sql.append("`");
        } else if (sqlType == SqlType.SELECT) {
            // select name,age from user where name="colagy"
            sql.append("select ");
            Class<?> wc = wherePojo.getClass();
            Field[] wcfields = wc.getDeclaredFields();

            int i = 0;
            for (Field field : wcfields) {
                field.setAccessible(true);
                String key = field.getName();
                sql.append(key);
                if (i < wcfields.length - 1) {
                    sql.append(" , ");
                }
                i++;
            }

            String tableName = parseTableName(wc);
            sql.append(" from ");
            sql.append(tableName);
        }

        // select name,age from user where name="colagy"
        // delete from user where name="colagy"
        // update user set name="colagy" where name="coalgy"
        // 查询 修改 删除 处理where
        if (wherePojo != null && (sqlType == SqlType.SELECT || sqlType == SqlType.UPDATE || sqlType == SqlType.DELETE)) {
            sql.append(" where 1=1 ");

            Class<?> wc = wherePojo.getClass();
            Field[] wcfields = wc.getDeclaredFields();

            for (Field field : wcfields) {
                field.setAccessible(true);
                String key = field.getName();
                Object value = null;
                try {
                    value = field.get(wherePojo);
                    if (value == null) {
                        continue;
                    }
                    sql.append(" and ");
                    sql.append(key);
                    sql.append(" = ?");
                    paramList.add(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return sql.toString();
    }

    /**
     * 从POJO的 @TableName 注解上获取表名
     *
     * @param clazz POJO 的 class
     * @return 表名
     */
    public static <T> String parseTableName(Class<T> clazz) {
        // 获取表名
        String tn = "";
        TableName tableName = clazz.getAnnotation(TableName.class);
        if (tableName == null || tableName.name() == null) {
            System.out.println("请在pojo类加 @TableName 注解");
            tn = clazz.getSimpleName().toLowerCase();
        } else {
            tn = tableName.name();
        }
        return tn;
    }

    /**
     * 往预编译statement传入参数
     *
     * @param statement 预编译 statement
     * @param params    参数列表
     * @throws SQLException
     */
    public static void parsePreparedStatement(PreparedStatement statement, Object... params) throws SQLException {
        int i = 1;
        for (Object o : parseParams(params)) {
            statement.setObject(i, o);
            i++;
        }
    }

    public static Object[] parseParams(Object... params) {
        Object[] ret = new Object[params.length];
        int i = 0;
        for (Object p : params) {
            if (p instanceof Number) {
                ret[i] = p;
            } else {
                ret[i] = p.toString();
            }
            i++;
        }
        return ret;
    }

    /**
     * 处理结果集
     *
     * @param resultSet  结果集
     * @param returnType 返回POJO的class
     * @return POJO
     * @throws SQLException
     */
    public static <T> T parseResultSet(ResultSet resultSet, Class<T> returnType) throws SQLException {
        if (resultSet.next()) {
            return parseReturnType(resultSet, returnType);
        } else {
            return null;
        }
    }

    /**
     * 处理结果集
     *
     * @param resultSet       结果集
     * @param collectionClass 集合类型 默认为ArrayList  支持 List 或者 Set 中可实例化的类 不要使用接口和抽象类 implements Collection
     * @param returnType      返回POJO的class
     * @return Collection<POJO>
     * @throws SQLException
     */
    public static <T, L> Collection<T> parseResultSet(ResultSet resultSet, Class<L> collectionClass,
        Class<T> returnType) throws SQLException {
        if (resultSet == null || returnType == null) {
            return null;
        }
        Collection<T> list;
        if (collectionClass == null) {
            list = new ArrayList<>();
        } else {
            try {
                list = (Collection<T>)collectionClass.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            } catch (InstantiationException e) {
                System.err.println("Interface or Abstract Class not allow here. 不要使用接口和抽象类接收结果列表");
                e.printStackTrace();
                return null;
            }
        }

        if (list == null) {
            return null;
        }

        while (resultSet.next()) {
            list.add(parseReturnType(resultSet, returnType));
        }
        return list;
    }

    /**
     * 反射生成 POJO
     *
     * @param resultSet  结果集
     * @param returnType POJO class
     * @return POJO
     */
    public static <T> T parseReturnType(ResultSet resultSet, Class<T> returnType) {
        T ret = null;
        try {
            ret = (T)returnType.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }

        Field[] fields = returnType.getDeclaredFields();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                f.set(ret, resultSet.getObject(f.getName(), f.getType()));
            } catch (IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 关闭资源
     *
     * @param resultSet 结果集
     */
    public static void close(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
            resultSet = null;
        }
    }

    /**
     * 关闭资源
     *
     * @param statement statement连接对象
     * @param resultSet 结果集
     */
    public static void close(Statement statement, ResultSet resultSet) throws SQLException {
        try {
            close(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }
    }

    /**
     * 关闭资源
     *
     * @param statement statement连接对象
     */
    public static void close(Statement statement) throws SQLException {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connectionStatus = ConnectionStatus.CLOSING.status;
            connection.close();
            connection = null;
            connectionStatus = ConnectionStatus.CLOSE.status;
        }
    }
}

