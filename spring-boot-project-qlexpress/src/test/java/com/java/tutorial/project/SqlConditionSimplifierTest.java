package com.java.tutorial.project;

import org.apache.calcite.config.Lex;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.logical.LogicalFilter;
import org.apache.calcite.rel.logical.LogicalProject;
import org.apache.calcite.rex.RexBuilder;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.rex.RexUtil;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Planner;
import org.apache.calcite.tools.RelConversionException;
import org.apache.calcite.tools.ValidationException;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlConditionSimplifierTest {

    @Test
    public void Test() {
        String sql =
            "SELECT * FROM users WHERE (age = 0) or (age > 0 and age <= 5) or (age > 5 and age <= 10) or (age > 10 and age <= 15) or (age > 15 and age <= 20) or (age > 20)";
        try {
            // 创建 Calcite 连接
            java.sql.Connection connection = DriverManager.getConnection("jdbc:calcite:");
            CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
            SchemaPlus rootSchema = calciteConnection.getRootSchema();

            // 注册 users 表
            Map<String, Object> tableMap = new HashMap<>();
            tableMap.put("age", Integer.class);
            rootSchema.add("users", new AbstractTable() {
                @Override
                public org.apache.calcite.rel.type.RelDataType getRowType(
                    org.apache.calcite.rel.type.RelDataTypeFactory typeFactory) {
                    org.apache.calcite.rel.type.RelDataTypeFactory.Builder builder = typeFactory.builder();
                    for (Map.Entry<String, Object> entry : tableMap.entrySet()) {
                        builder.add(entry.getKey(), typeFactory.createJavaType((Class<?>) entry.getValue()));
                    }
                    return builder.build();
                }
            });

            // 创建 Calcite 框架配置
            FrameworkConfig config = Frameworks.newConfigBuilder().defaultSchema(rootSchema)
                .parserConfig(org.apache.calcite.sql.parser.SqlParser.configBuilder().setLex(Lex.MYSQL).build())
                .build();
            Planner planner = Frameworks.getPlanner(config);

            // 解析 SQL 语句
            org.apache.calcite.sql.SqlNode sqlNode = planner.parse(sql);
            sqlNode = planner.validate(sqlNode);
            RelNode relNode = planner.rel(sqlNode).project();

            // 获取过滤条件
            RelNode inputNode = relNode;
            LogicalFilter filter = null;
            if (relNode instanceof LogicalProject) {
                inputNode = ((LogicalProject) relNode).getInput();
            }
            if (inputNode instanceof LogicalFilter) {
                filter = (LogicalFilter) inputNode;
            }

            if (filter != null) {
                RexNode condition = filter.getCondition();

                // 简化条件
                RexBuilder rexBuilder = filter.getCluster().getRexBuilder();
                List<RexNode> conjunctions = RelOptUtil.conjunctions(condition);
                RexNode simplifiedCondition = RexUtil.composeConjunction(rexBuilder, conjunctions, true);

                // 输出简化后的条件
                System.out.println("简化后的条件: " + simplifiedCondition);
            }

        } catch (SqlParseException | ValidationException e) {
            e.printStackTrace();
        } catch (RelConversionException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}