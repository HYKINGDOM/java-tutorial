package com.java.db.batch;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {

    public static void main(String[] args) throws SQLException {

        boolean loadDriver1 = DbUtils.loadDriver("com.mysql.cj.jdbc.Driver");

        Connection connection = JDBCUtil.createConnection("10.0.220.30:3306/talent_admin", "platform", "Platform_1314");



        System.out.println(loadDriver1);


    }
}
