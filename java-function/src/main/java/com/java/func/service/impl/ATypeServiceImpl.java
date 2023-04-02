package com.java.func.service.impl;

import com.java.func.service.TypeService;

import java.util.function.Function;

/**
 * @author HY
 */
public class ATypeServiceImpl implements TypeService {
    @Override
    public boolean isTypeBoolean(String type) {
        return "A".equals(type);
    }

    @Override
    public String getDataByDataBase(String type, Function<String, String> function) {

        return null;
    }
}
