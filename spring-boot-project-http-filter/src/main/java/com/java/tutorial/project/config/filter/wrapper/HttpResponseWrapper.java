package com.java.tutorial.project.config.filter.wrapper;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author kscs
 */
@Slf4j
public class HttpResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    private HttpServletResponse response;

    private PrintWriter printWriter;

    public HttpResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new MyServletOutputStream(bytes);
    }

    @Override
    public PrintWriter getWriter() {
        printWriter = new PrintWriter(new OutputStreamWriter(bytes, StandardCharsets.UTF_8));
        return printWriter;
    }

    /**
     * 获取缓存在 PrintWriter 中的响应数据
     *
     * @return
     */
    public byte[] getBytes() {
        if (null != printWriter) {
            printWriter.close();
            return bytes.toByteArray();
        }

        if (null != bytes) {
            try {
                bytes.flush();
            } catch (IOException e) {
                log.error("HttpResponseWrapper getBytes error:", e);
            }
        }
        assert bytes != null;
        return bytes.toByteArray();
    }

    public String getContent() {
        if (null != printWriter) {
            printWriter.close();
            return bytes.toString(StandardCharsets.UTF_8);
        }

        if (null != bytes) {
            try {
                bytes.flush();
            } catch (IOException e) {
                log.error("HttpResponseWrapper getContent error:", e);
            }
        }
        assert bytes != null;
        return bytes.toString(StandardCharsets.UTF_8);
    }

    class MyServletOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream outputStream;

        public MyServletOutputStream(ByteArrayOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void write(int b) {
            // 将数据写到 stream　中
            outputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }
    }

}
