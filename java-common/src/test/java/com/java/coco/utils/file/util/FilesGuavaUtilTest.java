package com.java.coco.utils.file.util;


import com.google.common.base.Charsets;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.google.common.io.MoreFiles;
import org.junit.jupiter.api.Test;


import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Guava的Files类中还提供了其他一些文件的简捷方法。比如
 *
 * touch方法创建或者更新文件的时间戳。
 * createTempDir()方法创建临时目录
 * Files.createParentDirs(File) 创建父级目录
 * getChecksum(File)获得文件的checksum
 * hash(File)获得文件的hash
 * map系列方法获得文件的内存映射
 * getFileExtension(String)获得文件的扩展名
 * getNameWithoutExtension(String file)获得不带扩展名的文件名
 * Guava的方法都提供了一些重载，这些重载可以扩展基本用法，我们也有必要去多了解一下，这些重载的方法。
 */
public class FilesGuavaUtilTest {


    private static String from = "D:\\自定义代码\\Guava\\guava\\src\\1.txt";
    private static String to = "D:\\自定义代码\\Guava\\guava\\src\\2.txt";

    @Test
    public void file_move_guava_test_path_02() throws IOException {

        Files.copy(new File(from), new File(to));


        Files.move(new File(from), new File(to));


        File file = new File(from);
        //读取文件第一行
        String configJson = Files.readFirstLine(file, Charsets.UTF_8);

        //将文件每一行读到list里
        List<String> readLines = Files.readLines(file, Charsets.UTF_8);

        //按照条件，将文件每行读到list里
        Files.readLines(file, Charsets.UTF_8, new LineProcessor<List<String>>() {
            List<String> list = new ArrayList<>();

            @Override
            public List<String> getResult() {
                return list;
            }

            @Override
            public boolean processLine(String arg0) throws IOException {
                // TODO Auto-generated method stub
                return false;
            }
        });


        //计算文件hashcode (可对比两个文件是否一样)
        //Hashing.md5();Hashing.sha256()
        HashCode hash = Files.asByteSource(new File(to)).hash(Hashing.sha512());
    }


    @Test
    public void file_find() {
        String path = "D:\\Program Files\\stable-diffusion-webui-1.0.0-pre";
        File file = new File(path);
        //获取path下子目录
        Iterable<File> childrens = Files.fileTraverser().breadthFirst(file);
        for (File children : childrens) {
            System.out.println("子目录: " + children);
        }

    }


    @Test
    public void file_find_path() {
        String path = "D:\\Program Files\\stable-diffusion-webui-1.0.0-pre";
        File file = new File(path);

        //获取path下所有目录  preOrderTraversal postOrderTraversal顺序不一样
        Iterable<File> files = Files.fileTraverser().depthFirstPreOrder(file);
        for (File file1 : files) {
            System.out.println("全目录: " + file1);
        }

    }


    @Test
    public void file_find_path_02() throws IOException {

        String path = "D:\\Program Files\\stable-diffusion-webui-1.0.0-pre";

        ImmutableList<Path> paths = MoreFiles.listFiles(Path.of(path));

        for (Path path1 : paths) {
            System.out.println("路径: " + path1);
        }

        String nameWithoutExtension = MoreFiles.getNameWithoutExtension(Paths.get("F:/foo.txt"));

        System.out.println(nameWithoutExtension);

    }

    @Test
    public void file_find_path_01() throws IOException {

        //获取扩展名
        String ext = Files.getFileExtension("F:/foo.txt");
        System.out.println(ext);

        //获得不带扩展名的文件名
        String fn = Files.getNameWithoutExtension("F:/foo.txt");
        System.out.println(fn);

        //创建或者更新文件的时间戳
        File configPath = new File("F:/app-release.apk");
        Files.touch(configPath);





    }
}