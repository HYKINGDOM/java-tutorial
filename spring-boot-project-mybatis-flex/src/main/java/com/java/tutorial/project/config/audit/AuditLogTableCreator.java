package com.java.tutorial.project.config.audit;

import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

@Getter
public class AuditLogTableCreator {

    private final static String sqlTemplate =
        "CREATE TABLE %s (" + "ID bigint(20) NOT NULL AUTO_INCREMENT," + "TableName varchar(50) DEFAULT NULL," + "ColumnName varchar(50) DEFAULT NULL," + "PrimaryKey bigint(20) DEFAULT NULL," + "ParentID bigint(20) DEFAULT NULL," + "NewValue VARCHAR(520) DEFAULT NULL," + "OldValue VARCHAR(520) DEFAULT NULL," + "Operation varchar(50) DEFAULT NULL," + "CreateTime datetime DEFAULT NULL," + "CreateClerk int(11) DEFAULT NULL," + "PRIMARY KEY (ID)) ENGINE=InnoDB;";

    private final String defaultTableName;

    private final String preTableName;

    private String currentValidTableName;

    private final Boolean splitEnable;

    public AuditLogTableCreator(Boolean splitEnable, String defaultTableName, String preTableName) {

        this.splitEnable = splitEnable;
        this.defaultTableName = defaultTableName;
        this.preTableName = preTableName;

        currentValidTableName = getCurrentTableName();
    }

    public String getCurrentTableName() {
        if (splitEnable) {
            Calendar calendar = Calendar.getInstance();
            String calendarMonth;
            if (calendar.get(Calendar.MONTH) < 9) {
                calendarMonth = "0" + (calendar.get(Calendar.MONTH) + 1);
            } else {
                calendarMonth = String.valueOf((calendar.get(Calendar.MONTH) + 1));
            }
            return preTableName + calendar.get(Calendar.YEAR) + calendarMonth;
        } else {
            return defaultTableName;
        }

    }

    public String createNew(Connection connection) {
        try {
            String tableNameByNowDate = getCurrentTableName();
            Statement statement = connection.createStatement();
            statement.execute(String.format(sqlTemplate, tableNameByNowDate));
            statement.close();
            currentValidTableName = tableNameByNowDate;
            return tableNameByNowDate;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
