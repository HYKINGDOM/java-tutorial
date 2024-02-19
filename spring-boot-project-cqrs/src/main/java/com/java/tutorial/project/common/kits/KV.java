package com.java.tutorial.project.common.kits;

import java.util.HashMap;
import java.util.Map;

/**
 * Map封装
 */
public final class KV extends HashMap<Object, Object> {

    public KV() {
    }

    public KV(Map<Object, Object> map) {
        putAll(map);
    }

    public static KV by(Object key, Object value) {
        return new KV().set(key, value);
    }

    public static KV by(Object key, Object value, Object defaultValue) {
        return new KV().set(key, value, defaultValue);
    }

    public Map<Object, Object> toMap() {
        return this;
    }

    public KV set(Object key, Object value) {
        put(key, value);

        return this;
    }

    public KV set(Object key, Object value, Object defaultValue) {
        if (value == null) {
            put(key, defaultValue);
        } else {
            put(key, value);
        }

        return this;
    }

    public String getStr(Object key) {
        Object obj = get(key);
        return obj == null ? null : obj.toString();
    }

    public Integer getInt(Object key) {
        Object obj = get(key);
        return obj == null ? null : ((Number)obj).intValue();
    }

    public Integer str2int(Object key) {
        Object obj = get(key);
        return obj == null ? null : Integer.valueOf((String)obj);
    }

    public Long getLong(Object key) {
        Object obj = get(key);
        return obj == null ? null : ((Number)obj).longValue();
    }

    public Long str2long(Object key) {
        Object obj = get(key);
        return obj == null ? null : Long.valueOf((String)obj);
    }

    public Double getDouble(Object key) {
        Object obj = get(key);
        return obj == null ? null : ((Number)obj).doubleValue();
    }

    public Double str2double(Object key) {
        Object obj = get(key);
        return obj == null ? null : Double.valueOf((String)obj);
    }

    public boolean isNull(Object key) {
        return get(key) == null;
    }

    public boolean notNull(Object key) {
        return get(key) != null;
    }

}
