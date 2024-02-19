package com.java.tutorial.project.common.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.val;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author HY
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(
        MethodArgumentNotValidException methodArgumentNotValidException) {
        String collect = methodArgumentNotValidException.getAllErrors().stream()
            .map(e -> String.format(Objects.requireNonNull(e.getDefaultMessage()), ((FieldError)e).getField()))
            .collect(Collectors.joining(", "));
        Map<String, Object> objectObjectHashMap = new HashMap<>(2);
        objectObjectHashMap.put("code", "500");
        objectObjectHashMap.put("data", collect);
        val gsonJsonParser = new Gson();
        return gsonJsonParser.toJson(objectObjectHashMap);
    }

}
