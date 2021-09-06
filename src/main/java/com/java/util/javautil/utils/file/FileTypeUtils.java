package com.java.util.javautil.utils.file;

/**
 * 媒体类型工具类
 *
 * @author yihur
 */
public class FileTypeUtils {

    public static final String[] IMAGE_EXTENSION = {"bmp", "gif", "jpg", "jpeg", "png",
            "BMP", "GIF", "JPG", "JPEG", "PNG"};

    public static final String[] FLASH_EXTENSION = {"swf", "flv"};

    public static final String[] VIDEO_EXTENSION = {"mp4", "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg", "asf", "rm", "rmvb",
            "MP4", "SWF", "FLV", "MP3", "WAV", "WMA", "WMV", "MID", "AVI", "MPG", "ASF", "RM", "RMVB"};

    public static final String[] DOC_EXTENSION = {"doc", "docx", "xls", "xlsx", "ppt", "pptx",
            "DOC", "DOCX", "XLS", "XLSX", "PPT", "PPTX"};

    public static final String[] WIN_RAR_EXTENSION = {"rar", "zip", "gz", "bz2", "7z",
            "RAR", "ZIP", "GZ", "BZ2", "7Z"};

    public static final String[] WEB_EXTENSION = {"html", "htm", "mhtml",
            "HTML", "HTM", "MHTML"};

    public static final String[] TXT_EXTENSION = {"txt", "TXT"};

    public static final String[] PDF_EXTENSION = {"pdf", "PDF"};

    public static final String[] TORRENT_EXTENSION = {"torrent", "TORRENT"};

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // pdf
            "pdf"};

}
