package com.java.tutorial.project.util;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author meta
 */
@Slf4j
public class ApachePdfBoxUtil {

    public static void main(String[] args) {
        String pdfPath = "G:\\empRequest\\Test.pdf";
        String txtPath = "G:\\empRequest\\Test.txt";
        int startPage = 1;
        int endPage = Integer.MAX_VALUE;
        boolean sortByPos = false;
        convertPdfToText(pdfPath, txtPath, startPage, endPage, sortByPos);
    }

    /**
     * 将PDF文件转换为文本文件
     *
     * @param pdfPath   PDF文件路径
     * @param txtPath   目标文本文件路径
     * @param startPage 开始提取页数（默认为1）
     * @param endPage   结束提取页数（默认为PDF总页数）
     * @param sortByPos 是否按位置排序
     */
    public static void convertPdfToText(String pdfPath, String txtPath, int startPage, int endPage, boolean sortByPos) {
        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFTextStripper pts = new PDFTextStripper();
            if (endPage > document.getNumberOfPages()) {
                endPage = document.getNumberOfPages();
            }
            pts.setStartPage(startPage);
            pts.setEndPage(endPage);
            pts.setSortByPosition(sortByPos);
            String content = pts.getText(document);
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(txtPath))) {
                writer.write(content);
            }
        } catch (Exception e) {
            log.error("Error converting PDF to text: {}", ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /**
     * 读取PDF文件内容
     *
     * @param path PDF文件路径
     * @return PDF文件内容
     */
    private static String getContentFromPdf(String path) {

        try (PDDocument document = PDDocument.load(new File(path))) {
            if (!document.isEncrypted()) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String text = pdfStripper.getText(document);
                log.info("Text: {}", text);
                return text;
            } else {
                log.info("The document is encrypted.");
                return null;
            }
        } catch (IOException e) {
            log.error("Error reading PDF: {}", ExceptionUtil.getRootCauseMessage(e));
        }

        return null;
    }
}