package com.java.coco.utils.file.util;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;

import java.io.File;
import java.util.List;

import static com.java.coco.utils.DBCAndSBC.ToDBC;
import static com.java.coco.utils.HalfAndFullConverseUtil.qj2bj;
import static com.java.coco.utils.file.constant.FileFormatNameConstant.getAllFileSymbolFormatName;

/**
 * @author HY
 */
public class FileNameUtils {

    /**
     * 替换掉文件名中特殊的字符串
     *
     * @param oldName 旧的文件名
     * @return 替换之后的文件名
     */
    public static String formatFileNameHandle(String oldName) {
        List<String> allFileSymbolFormatName = getAllFileSymbolFormatName();
        //全角转半角
        oldName = qj2bj(oldName);
        oldName = ToDBC(oldName);

        String newName = oldName;
        for (String str : allFileSymbolFormatName) {
            if (newName.contains(str)) {
                newName = oldName.replace(str, "");
            }
        }
        return newName;
    }

    /**
     * 修改文件
     *
     * @param toFile 需要修改的文件
     * @return 已修改后的文件
     */
    public static File rewriteRandomFileName(File toFile) {

        String type = FileTypeUtil.getType(toFile);

        String randomName = getRandomFileName() + "." + type;

        String rewriteFileName = renameFile(toFile.getPath(), randomName, toFile);

        assert rewriteFileName != null;
        File file = new File(rewriteFileName);
        return FileUtil.rename(file, randomName, false);
    }

    public static File rewriteFileName(File toFile, String newFileName) {

        String rewriteFileName = rewriteFileName(toFile.getPath(), newFileName);

        return FileUtil.rename(toFile, rewriteFileName, false);
    }

    /**
     * 通过文件路径直接修改文件名
     *
     * @param filePath    需要修改的文件的完整路径
     * @param newFileName 需要修改的文件的名称
     * @return 新的文件名
     */
    public static String rewriteFileName(String filePath, String newFileName) {
        File file = new File(filePath);
        // 判断原文件是否存在（防止文件名冲突）
        if (file.exists()) {
            newFileName = getRandomFileName();
        }

        newFileName = newFileName.trim();

        // 文件名不能为空
        if ("".equals(newFileName)) {
            newFileName = getRandomFileName();
        }

        return renameFile(filePath, newFileName, file);
    }

    private static String renameFile(String filePath, String newFileName, File file) {
        String substring = newFileName.substring(newFileName.lastIndexOf("."));
        if (!substring.isEmpty()) {
            newFileName = newFileName.replace(substring, "");
        }
        String newFilePath = null;

        // 判断是否为文件夹
        if (file.isDirectory()) {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName + filePath.substring(
                filePath.lastIndexOf(".")).toLowerCase();
        }
        File nf = new File(newFilePath);
        try {
            // 修改文件名
            boolean renameTo = file.renameTo(nf);
            System.out.println("文件名是否修改成功: " + renameTo);
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }

    private static String getRandomFileName() {
        String newFileName;
        long currentTimeMillis = System.currentTimeMillis();
        String value = String.valueOf(currentTimeMillis);
        String randomNumbers = RandomUtil.randomNumbers(5);
        newFileName = value + randomNumbers;
        return newFileName;
    }
}
