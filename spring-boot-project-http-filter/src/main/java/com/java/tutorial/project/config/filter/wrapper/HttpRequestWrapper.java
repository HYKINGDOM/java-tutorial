package com.java.tutorial.project.config.filter.wrapper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author meta
 */
@Slf4j
public class HttpRequestWrapper extends HttpServletRequestWrapper {

    private final ConcurrentMap<String, String> headers = new ConcurrentHashMap<>();

    @Getter
    private final String body;

    public HttpRequestWrapper(HttpServletRequest request) throws ServletException {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            log.error("Error reading input stream. ", ex);
            throw new ServletException("Error reading input stream.", ex);
        }
        body = stringBuilder.toString();
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                /**
                 * 方法的主要作用是告诉容器当前输入流是否已经准备好接收数据。具体来说：
                 * 异步处理：
                 * 当容器启动异步处理时，它会调用 isReady() 方法来检查输入流是否准备好接收数据。
                 * 如果 isReady() 返回 true，则表示输入流已经准备好接收数据。
                 * 如果 isReady() 返回 false，则表示输入流还没有准备好接收数据。
                 * 性能优化：
                 * 在某些情况下，如果输入流尚未准备好接收数据，容器可能会延迟处理或等待数据就绪。
                 * 返回 true 表示输入流始终准备好接收数据，这对于简单的同步处理是合适的。
                 */
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                /**
                 * 设置读取监听器：
                 * 在 setReadListener 方法中，我们通过 AsyncContext 启动一个异步任务。
                 * 在异步任务中，我们循环检查 isFinished 方法，直到数据读取完成。
                 * 当数据可用时，调用 readListener.onDataAvailable() 方法。
                 * 处理异步读取事件：
                 * 如果在读取过程中发生异常，调用 readListener.onError(e) 方法。
                 * 释放资源：
                 * 最后，通过 asyncContext.complete() 方法完成异步任务。
                 * 这样，setReadListener 方法就可以实现异步读取数据的功能，并且在数据可用时通知监听器。
                 */
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };

    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);

        if (headers.containsKey(name)) {
            value = headers.get(name);
        }

        return value;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headers.keySet());
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> list = Collections.list(super.getHeaders(name));
        if (headers.containsKey(name)) {
            list.add(headers.get(name));
        }
        return Collections.enumeration(list);
    }
}
