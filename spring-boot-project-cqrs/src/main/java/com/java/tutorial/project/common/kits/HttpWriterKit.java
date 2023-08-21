package com.java.tutorial.project.common.kits;

import java.io.IOException;
import java.io.PrintWriter;

import org.noear.snack.ONode;
import org.springframework.http.HttpStatus;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * Response输出信息封装
 *
 */
public final class HttpWriterKit {

    private static final String jsonContentType = "application/json;charset=UTF-8";

    public static void json(HttpServletResponse response, Object data) {
        writer(response, data, HttpStatus.OK);
    }

    public static void json(HttpServletResponse response, Object data, HttpStatus status) {
        writer(response, data, status);
    }

    public static void text(HttpServletResponse response, String str) {
        writer(response, str, HttpStatus.OK);
    }

    public static void text(ServletResponse response, String str, HttpStatus status) {
        writer((HttpServletResponse) response, str, status);
    }

    private static void writer(HttpServletResponse response, Object data, HttpStatus status) {
        response.setContentType(jsonContentType);
        response.setStatus(status.value());
        try (final PrintWriter writer = response.getWriter()) {
            if (data instanceof String) {
                String str =  (String) data;
                writer.write(str);
            } else {
                writer.write(ONode.stringify(data));
            }
            writer.flush();
        } catch (IOException e) {
        }
    }
}
