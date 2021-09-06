package com.java.util.javautil.utils.file;

import java.io.File;

import static cn.hutool.core.io.FileUtil.cleanEmpty;

/**
 * @author HY
 */
public class EmptyFileRemove {

    public void emptyFileListRemove(File file) {
        if (cleanEmpty(file)) {
            System.out.println("清除空文件夹成功");
        } else {
            System.out.println("清除空文件夹失败");
        }

    }
}
