package com.java.tutorial.project.common.json;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.noear.snack.ONode;
import org.noear.snack.core.Feature;
import org.noear.snack.core.Options;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

/**
 * 自定义SpringBoot JSON转换器
 */
public final class SnackHttpMessageConverter<T> implements HttpMessageConverter<T> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_JSON);
    }

    @Override
    public T read(Class<? extends T> clazz, HttpInputMessage inputMessage)
        throws IOException, HttpMessageNotReadableException {
        String json = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);

        return ONode.deserialize(json, clazz);
    }

    @Override
    public void write(T obj, MediaType contentType, HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        try (OutputStream output = outputMessage.getBody();) {
            Options options = Options.of(Feature.WriteNumberUseString, Feature.WriteDateUseFormat);
            byte[] bytes = ONode.stringify(obj, options).getBytes(StandardCharsets.UTF_8);
            for (int i = 0, size = bytes.length; i < size; ++i) {
                output.write(bytes[i]);
            }
            output.flush();
        } finally {

        }
    }

}
