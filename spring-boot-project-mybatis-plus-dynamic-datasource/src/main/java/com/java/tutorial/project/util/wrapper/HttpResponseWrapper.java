package com.java.tutorial.project.util.wrapper;

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

public class HttpResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    private HttpServletResponse response;
    private PrintWriter pwrite;

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
        pwrite = new PrintWriter(new OutputStreamWriter(bytes, StandardCharsets.UTF_8));
        return pwrite;
    }

    /**
     * 获取缓存在 PrintWriter 中的响应数据
     *
     * @return
     */
    public byte[] getBytes() {
        if (null != pwrite) {
            pwrite.close();
            return bytes.toByteArray();
        }

        if (null != bytes) {
            try {
                bytes.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assert bytes != null;
        return bytes.toByteArray();
    }

    public String getContent() throws UnsupportedEncodingException {
        if (null != pwrite) {
            pwrite.close();
            return bytes.toString(StandardCharsets.UTF_8);
        }

        if (null != bytes) {
            try {
                bytes.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assert bytes != null;
        return bytes.toString(StandardCharsets.UTF_8);
    }

    class MyServletOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream ostream;

        public MyServletOutputStream(ByteArrayOutputStream ostream) {
            this.ostream = ostream;
        }

        @Override
        public void write(int b) {
            // 将数据写到 stream　中
            ostream.write(b);
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
