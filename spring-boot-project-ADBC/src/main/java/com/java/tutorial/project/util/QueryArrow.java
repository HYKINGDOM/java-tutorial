package com.java.tutorial.project.util;

import org.apache.arrow.adbc.core.AdbcConnection;
import org.apache.arrow.adbc.core.AdbcDatabase;
import org.apache.arrow.adbc.core.AdbcStatement;
import org.apache.arrow.adbc.core.PartitionDescriptor;
import org.apache.arrow.adbc.drivermanager.AdbcDriverManager;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.ipc.ArrowReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryArrow {

    public static String getQueryArrow(String query) throws Exception {
        BufferAllocator allocator = new RootAllocator();
        AdbcDatabase database = null;
        AdbcConnection connection = null;

        try {
            // 1. 创建数据库连接（以SQLite为例）
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("filename", "example.db");

            // 需要驱动工厂类的全限定名（根据实际驱动配置）
            database = AdbcDriverManager.getInstance().connect(
                    "org.apache.arrow.adbc.driver.sqlite.SqliteDriverFactory",
                    allocator,
                    parameters
            );

            connection = database.connect();

            // 2. 执行查询
            AdbcStatement stmt = connection.createStatement();
            stmt.setSqlQuery(query);

            // 3. 处理结果
            AdbcStatement.QueryResult queryResult = stmt.executeQuery();
            StringBuilder result = new StringBuilder();

            try (ArrowReader reader = queryResult.getReader()) {
                while (reader.loadNextBatch()) {
                    result.append(reader.getVectorSchemaRoot().contentToTSVString());
                }
            }
            return result.toString();
        } finally {
            // 4. 清理资源
            if (connection != null) {

                connection.close();
            }
            if (database != null) {
                database.close();
            }
            allocator.close();
        }
    }

    public String getQueryArrow02(String query) throws Exception {
        BufferAllocator allocator = new RootAllocator();
        AdbcDatabase database = null;
        AdbcConnection connection = null;

        try {
            // 1. 创建数据库连接（以PostgreSQL为例）
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("host", "192.168.5.4");
            parameters.put("port", 5432);
            parameters.put("username", "test_demo");
            parameters.put("password", "hmW8eGQt7AD6Jm7a");
            parameters.put("dbname", "test_demo");

            database = AdbcDriverManager.getInstance().connect(
                    "org.apache.arrow.adbc.driver.postgresql.PostgresDriverFactory",
                    allocator,
                    parameters
            );




            connection = database.connect();

            // 2. 执行分区查询
            AdbcStatement stmt = connection.createStatement();
            stmt.setSqlQuery(query);

            // 3. 处理分区结果
            AdbcStatement.PartitionResult partitionResult = stmt.executePartitioned();
            List<PartitionDescriptor> partitions = partitionResult.getPartitionDescriptors();

            StringBuilder result = new StringBuilder();
            for (PartitionDescriptor partition : partitions) {
                try (ArrowReader reader = connection.readPartition(partition.getDescriptor())) {
                    while (reader.loadNextBatch()) {
                        result.append(reader.getVectorSchemaRoot().contentToTSVString());
                    }
                }
            }
            return result.toString();
        } finally {
            // 4. 清理资源
            if (connection != null) {
                connection.close();
            }
            if (database != null) {
                database.close();
            }
            allocator.close();
        }
    }
}