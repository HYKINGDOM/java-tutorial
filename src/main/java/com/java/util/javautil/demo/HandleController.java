package com.java.util.javautil.demo;

import org.springframework.boot.web.servlet.error.ErrorController;

public class HandleController implements ErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }
}
