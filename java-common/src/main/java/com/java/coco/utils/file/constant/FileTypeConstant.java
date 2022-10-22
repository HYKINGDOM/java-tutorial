package com.java.coco.utils.file.constant;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 媒体类型工具类
 *
 * @author yihur
 */
public class FileTypeConstant {

    /**
     * "图片": ["jpeg", "jpg", "tiff", "gif", "bmp", "png", "bpg", "svg",
     * "heif", "psd"],
     * "文档": ["oxps", "epub", "pages", "docx", "doc", "fdf", "ods",
     * "odt", "pwi", "xsn", "xps", "dotx", "docm", "dox",
     * "rvg", "rtf", "rtfd", "wpd", "xls", "xlsx", "ppt",
     * "pptx", "csv", ",pdf"],
     * "压缩文件": ["a", "ar", "cpio", "iso", "tar", "gz", "rz", "7z",
     * "dmg", "rar", "xar", "zip"],
     * "影音": ["aac", "aa", "aac", "dvf", "m4a", "m4b", "m4p", "mp3",
     * "msv", "ogg", "oga", "raw", "vox", "wav", "wma"],
     * "文本": ["txt", "in", "out"],
     * "编程": ["py", "html5", "html", "htm", "xhtml", "c", "cpp", "java", "css"],
     * "可执行程序": ["exe"],
     */


    public static Map<String, List<String>> getAllFileType() {
        List<String> list = new ArrayList<>();
        Map<String, List<String>> listMap = new HashMap<>(16);
        listMap.put("图片", List.of(IMAGE_EXTENSION));
        listMap.put("视频", List.of(VIDEO_EXTENSION));
        listMap.put("文档", List.of(DOC_EXTENSION));
        listMap.put("网页", List.of(WEB_EXTENSION));
        listMap.put("图书", List.of(TXT_EXTENSION));
        listMap.put("种子", List.of(TORRENT_EXTENSION));
        listMap.put("压缩文件", List.of(ZIP_EXTENSION));

        list.addAll(List.of(IMAGE_EXTENSION));
        list.addAll(List.of(VIDEO_EXTENSION));
        list.addAll(List.of(DOC_EXTENSION));
        list.addAll(List.of(WEB_EXTENSION));
        list.addAll(List.of(TXT_EXTENSION));
        list.addAll(List.of(TORRENT_EXTENSION));
        list.addAll(List.of(ZIP_EXTENSION));
        listMap.put("其他", list);

        return listMap;
    }


    public static final String[] IMAGE_EXTENSION = {"jpeg", "jpg", "tiff", "gif", "bmp", "png", "bpg", "svg", "heif", "psd"};

    public static final String[] VIDEO_EXTENSION = {"mp4", "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
            "asf", "rm", "rmvb", "mkv", "mov", "ts", "mpeg", "mts", "3gp", "m4a", "m4b", "m4p"};

    public static final String[] DOC_EXTENSION = {"doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf"};

    public static final String[] WEB_EXTENSION = {"html", "htm", "mhtml"};

    public static final String[] TXT_EXTENSION = {"txt", "chm", "epub"};
    public static final String[] TORRENT_EXTENSION = {"torrent"};

    public static final String[] ZIP_EXTENSION = {"rar", "zip", "gz", "bz2", "7z", "dmg"};


}
