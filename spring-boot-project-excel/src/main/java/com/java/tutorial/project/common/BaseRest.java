package com.java.tutorial.project.common;

/**
 * @author meta
 */
public class BaseRest {

    public Result addSucResult() {
        return this.addResult(true, ResultCodeEnum.SUCCESS.getValue(), ResultCodeEnum.SUCCESS.getLabel(), (Object)null);
    }

    public <T> Result addResult(boolean result, String code, String message, T data) {
        Result<T> rs = new Result();
        rs.setResult(result);
        rs.setCode(code);
        rs.setMessage(message);
        rs.setData(data);
        return rs;
    }
}
