package com.java.coco.utils.file;

import java.util.List;

/**
 * 媒体类型工具类
 *
 * @author yihur
 */
public class FileTypeUtils {

    public static final List<String> FILE_SYMBOL_LIST = List.of("-", "@", "「", "」", "《", "》", "【", "】", "[", "]", "『"
            , "』", "〖", "〗", "▌", "（", "）", "。", "“", "”", "，", "~", "#", "!", "！", "+", "=");


    public static final List<String> FILTER_STRING_LIST = List.of("SOUSHU555.NET", "SOUSHU555.COM", "SOUSHU555.ORG", "SOUSHU2021.COM",
            "2048社区-BIG2048.COM", "THZU.CC", "2048社区", "BIG2048.COM", "BT-BTT.COM", "AI高清2K修复", "BBS.YZKOF.COM", "UUC82.COM", "1024核工厂",
            "FUN2048.COM", "THZU.CCTM", "THZU.CC", "原创", "重磅泄露", "最新", "更新", "NYAP2P.COM", "BT-BTT.COM", "SOAV.COM",
            "UNCENSORED", "LEAKED", ".HD", "每日更新", "每日", "799DVD.COM", "606DVD.COM", "136DVD.COM", "136DVD.COM", "909DVD.COM", "132DVD.COM"
            , "FULIBUS.NET", "91视频", "91自拍", "国产自拍", "KiKi", "魔性论坛", "WWW.MOX.LIFE", "2048论坛BBS2048.ORG", "91大神猫先生千人斩之", "SEXFLEXVIDEO",
            "YONITALE.COM", "BBS2048.ORG", ".COM", "ONLYFANS", "EAPK.XYZ", "❤", "GUOCHAN2048", "ThZu.Cc", "[", "]", "", "【", "】", "8899XX.XYZ",
            "jav20s8.com","guochan2048.com","soushu555.net","soushu555.com","soushu555.org","soushu2021.com","soushu2022.com","搜书吧网址");

    public static final String[] IMAGE_EXTENSION = {"bmp", "gif", "jpg", "jpeg", "png",
            "BMP", "GIF", "JPG", "JPEG", "PNG"};

    public static final String[] FLASH_EXTENSION = {"swf", "flv"};

    public static final String[] VIDEO_EXTENSION = {"MP4", "SWF", "FLV", "MP3", "WAV", "WMA", "WMV", "MID", "AVI", "MPG",
            "ASF", "RM", "RMVB", "MKV", "MOV", "TS","MPEG","MTS","3GP"};

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
