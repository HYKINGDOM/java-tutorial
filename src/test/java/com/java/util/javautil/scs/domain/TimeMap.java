package com.java.util.javautil.scs.domain;

import java.time.LocalDateTime;

public class TimeMap {
    private int id;
    private LocalDateTime localDateTime;
    private Integer sortNum;

    public TimeMap(int id, LocalDateTime localDateTime, Integer sortNum) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.sortNum = sortNum;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
