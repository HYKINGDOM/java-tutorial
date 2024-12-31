package com.java.tutorial.project.controller;

import com.java.coco.common.Result;
import com.java.tutorial.project.config.TesseractOcrModelService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

/**
 * Ocr-控制器
 *
 * @author : YiFei
 */
@RestController
@RequestMapping("ocr")
@RequiredArgsConstructor
public class OcrController {

    private final TesseractOcrModelService tesseractOcrModelService;

    /**
     * 图片调整推荐 : 二值化：将图像转换为黑白，有助于提高对比度。 去噪：去除图像中的噪声。 旋转矫正：确保图像中的文本是水平的。
     *
     * @param file
     * @return
     */
    @PostMapping("/detection")
    public Result<String> ocrDetection(MultipartFile file) {
        try {
            Tesseract tesseract = tesseractOcrModelService.getTesseract();
            return Result.success(tesseract.doOCR(ImageIO.read(file.getInputStream())));
        } catch (Exception e) {
            throw new RuntimeException("ImageIO.read(file.getInputStream())) 解析错误");
        }
    }
}

