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
 * @author kscs
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
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
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
