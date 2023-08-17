package com.java.tutorial.project.http;

import com.google.common.collect.Lists;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.List;

/**
 * @author hy
 */
public class FileDownload {


    public static final List<String> IMAGE_EXTENSION_LOW = Lists.newArrayList("jpeg", "jpg", "tiff", "gif", "bmp", "png", "bpg", "svg", "heif", "psd");


    public static final List<String> IMAGE_EXTENSION_TOP = Lists.newArrayList("JPEG", "JPG", "TIFF", "GIF", "BMP", "PNG", "BPG", "SVG", "HEIF", "PSD");



    /**
     * 下载并保存图片
     *
     * @param imgUrl
     * @throws IOException
     */
    public static void downloadImage(String imgUrl) throws IOException {

        URL url = new URL(imgUrl);

        File file = new File("K:" + File.separator + Calendar.getInstance().getTimeInMillis() + ".jpeg");

        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }
    }

    /**
     * 下载并保存视频 打印下载进度
     *
     * @param savePath
     * @param response
     * @throws IOException
     */
    public static void saveVideoFileProgress(String savePath, HttpResponse<InputStream> response) throws IOException {
        HttpHeaders headers = response.headers();

        long contentLength = headers.firstValueAsLong("Content-Length").orElse(-1L);

        Path filePath = Path.of(savePath);
        Files.createDirectories(filePath.getParent());

        try (InputStream inputStream = response.body();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            // 自动创建目录结构并创建文件
            Files.createFile(filePath);

            byte[] buffer = new byte[8192];
            int bytesRead;
            long totalBytesRead = 0;

            try (OutputStream outputStream = Files.newOutputStream(filePath)) {
                while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                    double progress = (double)totalBytesRead / contentLength * 100;
                    System.out.printf("Download progress: %.2f%%\r", progress);
                }
            }
        } finally {
            // 手动关闭输入流
            response.body().close();
        }

        System.out.println("save Video File successfully!");
    }

    /**
     * 保存视频文件
     * @param savePath
     * @param response
     * @throws IOException
     */
    public static void saveVideoFile(String savePath, HttpResponse<InputStream> response) throws IOException {

        Path filePath = Path.of(savePath);
        Files.createDirectories(filePath.getParent());

        try (InputStream inputStream = response.body();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

            // 自动创建目录结构并创建文件
            Files.createFile(filePath);
            Files.copy(bufferedInputStream, filePath, StandardCopyOption.COPY_ATTRIBUTES);
        } finally {
            // 手动关闭输入流
            response.body().close();
        }

        System.out.println("save Video File successfully!");
    }
}
