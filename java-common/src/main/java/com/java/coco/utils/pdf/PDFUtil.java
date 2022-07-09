package com.java.coco.utils.pdf;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class PDFUtil {

    public static void main(String[] args) {
        try {
            // 是否排序
            boolean sort = false;
            // 开始提取页数
            int startPage = 1;
            // 结束提取页数
            int endPage = Integer.MAX_VALUE;
            String content = null;
            PrintWriter writer = null;
            //pdf文本路径
            String path = "G:\\empRequest\\Test.pdf";
            //输出txt文本路径
            String target="G:\\empRequest\\Test.txt";
            PDDocument document = PDDocument.load(new File(path));
            PDFTextStripper pts = new PDFTextStripper();
            endPage = document.getNumberOfPages();
            System.out.println("Total Page: " + endPage);
            pts.setStartPage(startPage);
            pts.setEndPage(endPage);
            pts.setSortByPosition(sort);
            try {
                //content就是从pdf中解析出来的文本
                content = pts.getText(document);
                writer = new PrintWriter(new FileOutputStream(target));
                writer.write(content);// 写入文件内容
                writer.flush();
                writer.close();
            } finally {
                document.close();
            }
            System.out.println("Get PDF Content ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}