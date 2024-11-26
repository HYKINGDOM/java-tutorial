package com.java.tutorial.project.util;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApachePdfBoxUtilTest {

    private static final String TEST_PDF_PATH = "D:\\download\\提示词工程（OpenAI 汉化版）.pdf";
    private static final String TEST_TXT_PATH = "D:\\download\\提示词工程（OpenAI 汉化版）.txt";

    @Test
    void testConvertPdfToText() throws IOException {
        // 测试正常情况
        ApachePdfBoxUtil.convertPdfToText(TEST_PDF_PATH, TEST_TXT_PATH, 1, Integer.MAX_VALUE, false);

        // 读取生成的文本文件内容
        String actualContent = FileUtils.readFileToString(new File(TEST_TXT_PATH), StandardCharsets.UTF_8);

        // 预期内容（这里假设你知道PDF的内容）
        String expectedContent = "预期的PDF文本内容";

        // 断言实际内容与预期内容一致
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void testConvertPdfToTextWithSpecificPages() throws IOException {
        // 测试指定页数的情况
        ApachePdfBoxUtil.convertPdfToText(TEST_PDF_PATH, TEST_TXT_PATH, 2, 3, false);

        // 读取生成的文本文件内容
        String actualContent = FileUtils.readFileToString(new File(TEST_TXT_PATH), StandardCharsets.UTF_8);

        // 预期内容（这里假设你知道PDF的第2页和第3页的内容）
        String expectedContent = "预期的PDF第2页和第3页的文本内容";

        // 断言实际内容与预期内容一致
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void testConvertPdfToTextWithSorting() throws IOException {
        // 测试按位置排序的情况
        ApachePdfBoxUtil.convertPdfToText(TEST_PDF_PATH, TEST_TXT_PATH, 1, Integer.MAX_VALUE, true);

        // 读取生成的文本文件内容
        String actualContent = FileUtils.readFileToString(new File(TEST_TXT_PATH), StandardCharsets.UTF_8);

        // 预期内容（这里假设你知道按位置排序后的PDF内容）
        String expectedContent = "预期的按位置排序后的PDF文本内容";

        // 断言实际内容与预期内容一致
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void testConvertPdfToTextWithInvalidPages() {
        // 测试无效页数的情况
        assertThrows(IllegalArgumentException.class, () -> {
            ApachePdfBoxUtil.convertPdfToText(TEST_PDF_PATH, TEST_TXT_PATH, -1, 5, false);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ApachePdfBoxUtil.convertPdfToText(TEST_PDF_PATH, TEST_TXT_PATH, 5, 1, false);
        });
    }

    @Test
    void testConvertPdfToTextWithNonExistentFile() {
        // 测试不存在的PDF文件
        assertThrows(IOException.class, () -> {
            ApachePdfBoxUtil.convertPdfToText("G:\\empRequest\\NonExistent.pdf", TEST_TXT_PATH, 1, Integer.MAX_VALUE, false);
        });
    }
}