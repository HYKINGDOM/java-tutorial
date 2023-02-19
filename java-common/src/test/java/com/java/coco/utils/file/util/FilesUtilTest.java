package com.java.coco.utils.file.util;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

public class FilesUtilTest {


    @Test
    public void file_test_path_01(){
        System.out.println("文件路径分隔符：" + File.separator);
        System.out.println("环境变量分隔符：" + File.pathSeparator);
    }



    @Test
    public void file_move_guava_test_path_02() throws IOException {
        //Files.copy(fromPath,toPath);
        // 将E:\\A.txt复制到F:\\A.txt
        Files.copy(Paths.get("E:\\A.txt"), (OutputStream) Paths.get("F:\\A.txt"));


        //Files.move(fromPath,toPath);


        Files.move(Paths.get("E:\\A.txt"), Paths.get("F:\\A.txt"));//将E:\\A.txt移动到F:\\A.txt

        //覆盖已有的目标路径，使用StandardCopyOption.REPLACE_EXISTING；例如：
        Files.move(Paths.get("E:\\A.txt"), Paths.get("F:\\A.txt"), StandardCopyOption.REPLACE_EXISTING);


        //Files.delete(path);

        Files.delete(Paths.get("E:\\A.txt"));//删除E:\\A.txt

        //如果删除文件不存在，会抛出异常java.nio.file.NoSuchFileException。因此，可以使用deleteIfExists(path)方法：
        boolean deleted = Files.deleteIfExists(Path.of("E:\\A.txt"));

    }
}