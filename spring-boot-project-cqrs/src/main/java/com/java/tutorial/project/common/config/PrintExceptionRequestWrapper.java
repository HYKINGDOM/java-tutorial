package com.java.tutorial.project.common.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * 自定义Request包装类
 */
public final class PrintExceptionRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    /**
     * 解决request.getInputStream()不能多次读取的问题
     */
    public PrintExceptionRequestWrapper(HttpServletRequest req) throws IOException {
        super(req);
        body = StreamUtils.copyToString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream(body.getBytes());

        return new ServletInputStream() {
            private boolean finished = false;

            @Override
            public boolean isFinished() {
                return finished;
            }

            @Override
            public int available() throws IOException {
                return stream.available();
            }

            @Override
            public void close() throws IOException {
                super.close();
                stream.close();
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int read() throws IOException {
                int data = stream.read();
                if (data == -1) {
                    finished = true;
                }
                return data;
            }
        };
    }

}
