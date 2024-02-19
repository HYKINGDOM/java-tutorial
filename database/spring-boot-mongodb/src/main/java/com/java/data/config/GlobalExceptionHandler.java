package com.java.data.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Gson gsonFormat = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(
        MethodArgumentNotValidException methodArgumentNotValidException) {
        String collect = methodArgumentNotValidException.getAllErrors().stream()
            .map(e -> String.format(Objects.requireNonNull(e.getDefaultMessage()), ((FieldError)e).getField()))
            .collect(Collectors.joining(", "));
        HashMap<String, Object> objectObjectHashMap = new HashMap<>(2);
        objectObjectHashMap.put("code", "500");
        objectObjectHashMap.put("data", collect);
        return gsonFormat.toJson(objectObjectHashMap);
    }

}
