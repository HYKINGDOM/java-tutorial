package com.java.tutorial.project.config;

import cn.hutool.db.Db;
import com.mysql.cj.jdbc.JdbcConnection;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConnectionFactory implements PooledObjectFactory<Connection> {

    private final String url;
    private final String username;
    private final String password;

    public JdbcConnectionFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public Connection create() throws SQLException {

        Db use = Db.use();
        Connection connection = use.getConnection();
        return use.getConnection();
    }


    public PooledObject<Connection> wrap(Connection connection) {
        return new DefaultPooledObject<>(connection);
    }

    @Override
    public void activateObject(PooledObject<Connection> pooledConnection) throws SQLException {
        // 这里可以做激活前的检查，如ping数据库确保连接可用
    }

    @Override
    public void passivateObject(PooledObject<Connection> pooledConnection) throws SQLException {
        // 这里可以做一些清理工作，如关闭连接上的事务
    }

    @Override
    public boolean validateObject(PooledObject<Connection> pooledConnection) {
        // 验证连接是否仍然有效，例如尝试执行一个简单的SQL查询
        try {
            return pooledConnection.getObject().isValid(5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroyObject(PooledObject<Connection> pooledConnection) throws SQLException {
        // 销毁连接，例如关闭连接
        pooledConnection.getObject().close();
    }

    @Override
    public PooledObject<Connection> makeObject() throws Exception {
        return null;
    }
}
