package com.java.tutorial.project.common.kits;

import java.util.List;

import org.beetl.sql.core.query.interfacer.StrongValue;

public final class QueryKit {

    /**
     * 判断查询条件的值是否为空集合
     *
     * @param value
     * @return
     */
    public static StrongValue in(List<?> value) {
        return new StrongValue() {
            @Override
            public boolean isEffective() {
                return value.size() > 0;
            }

            @Override
            public Object getValue() {
                return value;
            }
        };
    }

    /**
     * 判断查询条件的值是否为空字符串并拼接%
     *
     * @param value
     * @return
     */
    public static StrongValue like(String value) {
        return new StrongValue() {
            @Override
            public boolean isEffective() {
                return StrKit.notBlank(value);
            }

            @Override
            public Object getValue() {
                return "%" + value + "%";
            }
        };
    }

    /**
     * 判断查询条件的值是否为NULL
     *
     * @param value
     * @return
     */
    public static StrongValue notNull(Object value) {
        return new StrongValue() {
            @Override
            public boolean isEffective() {
                return value != null;
            }

            @Override
            public Object getValue() {
                return value;
            }
        };
    }

    /**
     * 判断查询条件的值是否为空字符串
     *
     * @param value
     * @return
     */
    public static StrongValue notBlank(String value) {
        return new StrongValue() {
            @Override
            public boolean isEffective() {
                return StrKit.notBlank(value);
            }

            @Override
            public Object getValue() {
                return value;
            }
        };
    }

}
