package com.java.func.service;

import java.util.function.Function;

public interface TypeService {

    boolean isTypeBoolean(String type);

    String getDataByDataBase(String type, Function<String, String> function);
}
