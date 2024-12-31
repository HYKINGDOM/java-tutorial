package com.java.tutorial.project.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import static net.sourceforge.tess4j.ITessAPI.TessOcrEngineMode.OEM_TESSERACT_LSTM_COMBINED;

/**
 * TesseractOcr 模型加载
 *
 * @author : YiFei
 */
@Slf4j
@Getter
@Component
public class TesseractOcrModelService {

    private final Tesseract tesseract = new Tesseract();

    public TesseractOcrModelService() {
        try {
            // 获取训练模型文件夹 （该方法在打包为jar后会有问题，建议使用项目中TensorflowUtil工具类）
            String folderPath = new ClassPathResource("tess_data").getFile().getAbsolutePath();
            /*
             * OEM_TESSERACT_ONLY = 0：表示仅运行Tesseract OCR引擎，不使用LSTM（Long Short-Term Memory）线识别器。Tesseract是一种传统的OCR引擎，适用于一般的文字识别任务。
             * OEM_LSTM_ONLY = 1：表示仅运行LSTM线识别器，不使用Tesseract。LSTM是一种深度学习模型，通常在处理复杂文本或手写文字识别等任务时表现较好。
             * OEM_TESSERACT_LSTM_COMBINED = 2：表示同时运行Tesseract和LSTM识别器，并在遇到困难情况时允许回退到Tesseract。这种组合模式可以在不同情况下灵活地选择最适合的识别引擎。
             * OEM_DEFAULT = 3：当调用 init_*() 方法时指定此模式，表示可以根据语言特定配置、命令行配置等自动推断使用哪种模式。如果没有明确指定，则默认使用 OEM_TESSERACT_ONLY 模式。
             */
            tesseract.setPageSegMode(OEM_TESSERACT_LSTM_COMBINED);
            // 设置Tesseract OCR引擎的训练数据文件夹路径
            /*
             * chi_sim.traineddata: Chinese Simplified（中文简体）
             * chi_sim_vert.traineddata: Chinese Simplified Vertical（中文简体竖排）
             * chi_tra.traineddata: Chinese Traditional（中文繁体）
             * chi_tra_vert.traineddata: Chinese Traditional Vertical（中文繁体竖排）
             */
            tesseract.setDatapath(folderPath);
            tesseract.setPageSegMode(6);
            // 设置为中文简体
            tesseract.setLanguage("chi_sim");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

