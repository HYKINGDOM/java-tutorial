package com.java.util.javautil.utils.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static com.java.util.javautil.utils.HalfAndFullConverseUtil.qj2bj;
import static com.java.util.javautil.utils.file.FileTypeUtils.VIDEO_EXTENSION;
import static com.java.util.javautil.utils.file.FileUtil.fileDirectoryLists;

/**
 * @author HY
 */
public class VideoFileSwitch {

    private static String EMPTY_STRING = "";

    private static String SYMBOL_STRIKETHROUGH_ONE = "-";

    private List<String> CONSTANT_FILE_TYPE = new ArrayList<>(Arrays.asList(VIDEO_EXTENSION));

    public static final String[] VIDEO_CLASSIFICATION_THREE = {"ABP", "ABS", "ABW", "ADN", "AEG", "AFS", "BBI", "BDA", "BEB",
            "BGN", "BMW", "CHN", "CHO", "CRS", "CWP", "DCV", "DDD", "DDH", "DFE", "DGL", "DIC", "DRG", "EHM", "EKW", "EMP", "FAD", "FC2",
            "FIR", "GVG", "GVH", "HAR", "HEY", "HHH", "HMN", "HND", "HPP", "HRV", "IMG", "ION", "IPX", "IPZ", "JAC", "JNT", "JUC", "JUL",
            "JUP", "JUX", "JUY", "KUF", "LAF", "LLR", "MDS", "MFC", "MIX", "MKD", "MKZ", "MMB", "MUM", "NDV", "NTK", "NTR", "OAE", "OHO",
            "OVG", "OYC", "PGD", "PHD", "PMX", "PPT", "RAW", "RBD", "RCT", "RED", "RHJ", "RKI", "RVG", "S2M", "SCR", "SFV", "SGA", "SHH",
            "SHS", "SHX", "SIS", "SKY", "SMD", "SOE", "TAP", "TCD", "TEK", "THZ", "TRE", "TRP", "UMD", "URE", "VEC", "VGD", "XND", "XRW",
            "YRZ", "YSN", "ZEX"};

    public static final String[] VIDEO_CLASSIFICATION_FOUR = {"SDSI", "2016", "AKDL", "AMBS", "ANCI", "APNS", "ATFB", "ATGO",
            "ATID", "AVOP", "BBAN", "BDSR", "BOBB", "CAWD", "CEMD", "CESD", "CHAA", "CHRV", "CJOB", "CJOD", "CLUB", "CSCT", "CWDV",
            "CZBD", "DASD", "DCRY", "DKDN", "DKSB", "DMOW", "DOCP", "DOKS", "DPHN", "DPMI", "DPMX", "DVAJ", "EBOD", "EYAN", "FCDC",
            "FFFD", "FGAN", "FINH", "FNEO", "FSET", "GANA", "GENT", "GONE", "HARU", "HDKA", "HHKL", "HJMO", "HMGL", "HMJM", "HNDB",
            "HNDS", "HNTV", "HODV", "HPOW", "HTMS", "HUNT", "HXAD", "HZGD", "IDBD", "IENE", "IENF", "IPIT", "IPSD", "IPVR", "IPXB",
            "JUFD", "JUFE", "KAWD", "KFNE", "KIRE", "KMRE", "KNMB", "KRMV", "KRND", "KTDS", "KUSE", "LCBD", "LLDV", "LULU", "LUXU",
            "MAAN", "MDTM", "MDYD", "MEYD", "MIAA", "MIAD", "MIAE", "MIDD", "MIDE", "MIFD", "MIGD", "MIMK", "MIRD", "MISA", "MISM",
            "MIUM", "MIZD", "MKBD", "MKDV", "MKMP", "MMGH", "MMND", "MRSS", "MSFH", "MUDR", "MUGF", "MVSD", "MXGS", "NACR", "NACX",
            "NASH", "NGOD", "NHDT", "NNPJ", "NSPS", "NTTR", "OFJE", "OKAX", "ONSD", "ONSG", "OREC", "OTIM", "PKPD", "PPBD", "PPPD",
            "PRED", "QIAN", "RCTD", "REAL", "REBD", "REZD", "RHTS", "SDAB", "SDDE", "SDJS", "SDMM", "SDMU", "SDNM", "SDTH", "SERO",
            "SHKD", "SIMM", "SIRO", "SIVR", "SKMJ", "SKSK", "SMBD", "SMDV", "SNIS", "SORA", "SPOR", "SQTE", "SSHN", "SSIS", "SSKP",
            "SSNI", "SSPD", "STAR", "SUKE", "SW61", "TBTB", "TIKB", "TSDS", "UMSO", "URKK", "URVK", "VEMA", "VENU", "VENX", "VICD",
            "VNDS", "VRTM", "WAAA", "WANZ", "XRLE", "XVSR", "YMDD", "ZIZG", "ZMAR", "ZMEN"};

    public static final String[] VIDEO_CLASSIFICATION_FIVE = {"AKAHH", "ATKDB", "CARIB", "CTNOZ", "CWPBD", "CZ015", "DANDY",
            "DFSSD", "DRGBD", "DVDES", "FSDSS", "H4610", "HEYZO", "HOISW", "HUNTA", "HUNTB", "LAFBD", "MXSPS", "N0464", "N0476",
            "N1222", "NHDTA", "NHDTB", "OFJEA", "S2MBD", "SDJS1", "SKYHD", "SSNI7", "STARS", "SVDVD", "SWEET", "TOKYO", "XVSR6"};

    /**
     * 特殊字符
     */
    public static final String[] CONSTANT_FILE_NAME_ARRAY_SYMBOL = {"---", "@", "「", "」", "《", "》", "【", "】", "[", "]", "『"
            , "』", "〖", "〗", "▌", "（", "）", "。", "“", "”", "，", "~", "#", "!", "！", "+", "="};

    /**
     * 特殊字段
     */
    public static final String[] CONSTANT_FILE_NAME_ARRAY = {"SOUSHU555.NET", "SOUSHU555.COM", "SOUSHU555.ORG", "SOUSHU2021.COM",
            "2048社区-BIG2048.COM", "THZU.CC", "2048社区", "BIG2048.COM", "BT-BTT.COM", "AI高清2K修复", "BBS.YZKOF.COM", "UUC82.COM", "1024核工厂",
            "FUN2048.COM", "THZU.CCTM", "THZU.CC", "原创", "重磅泄露", "最新", "更新", "NYAP2P.COM", "BT-BTT.COM", "SOAV.COM",
            "UNCENSORED", "LEAKED", ".HD", "每日更新", "每日", "799DVD.COM", "606DVD.COM", "136DVD.COM", "136DVD.COM", "909DVD.COM", "132DVD.COM"
            , "FULIBUS.NET", "91视频", "91自拍", "国产自拍", "KiKi", "魔性论坛", "WWW.MOX.LIFE", "2048论坛BBS2048.ORG", "91大神猫先生千人斩之","SEXFLEXVIDEO",
            "YONITALE.COM","BBS2048.ORG",".COM","ONLYFANS","EAPK.XYZ"};

    public static final List<String> CONSTANT_FILE_NAME_LIST = new ArrayList<>(Arrays.asList(CONSTANT_FILE_NAME_ARRAY));

    public static final Map<String, String> MAP_OF_STR = new HashMap<>();

    static {
        MAP_OF_STR.put("--", SYMBOL_STRIKETHROUGH_ONE);
        MAP_OF_STR.put("_", SYMBOL_STRIKETHROUGH_ONE);
        MAP_OF_STR.put("FC2-PPV", "FC2PPV");
        //MAP_OF_STR.put("00", "-");
        MAP_OF_STR.put("玩偶姐姐","HONGKONGDOLL");
    }


    private List<File> fileList = new ArrayList<>();


    public void videoFile() {
        File file = FileUtil.file("H:\\迅雷下载\\精东影业之密友");
        File[] files = file.listFiles();

        List<File> fileDirectoryLists = fileDirectoryLists(files, fileList);

        for (File file1 : fileDirectoryLists) {
            System.out.println("文件路径： " + file1.getPath());
        }
        System.out.println("==========---------------------------==========");

        for (File rootPathFile : fileDirectoryLists) {
            String type = FileTypeUtil.getType(rootPathFile).toUpperCase();
            if (CONSTANT_FILE_TYPE.contains(type)) {
                System.out.println("修改前: " + rootPathFile.getName()); 
                String rootPathFileName = getFileName(rootPathFile.getName());
                rootPathFileName = qj2bj(rootPathFileName);
                System.out.println("修改中: " + rootPathFileName);
                fixFileName(rootPathFile.getPath(), rootPathFileName);
                //rename(rootPathFile, rootPathFileName, true);
                System.out.println("修改后: " + rootPathFile.getName());
                System.out.println("====================");
            }
        }
    }


    private String getFileName(String fileName) {
        fileName = fileName.replaceAll(" ", "").toUpperCase();

        for (String strFile : CONSTANT_FILE_NAME_LIST) {
            if (fileName.contains(strFile)) {
                fileName = replaceName(fileName, strFile.toUpperCase(), EMPTY_STRING);
            }
        }

        for (String strFile : CONSTANT_FILE_NAME_ARRAY_SYMBOL) {
            if (fileName.contains(strFile)) {
                fileName = replaceName(fileName, strFile.toUpperCase(), EMPTY_STRING);
            }
        }

        for (Map.Entry<String, String> stringStringEntry : MAP_OF_STR.entrySet()) {
            if (fileName.contains(stringStringEntry.getKey())) {
                fileName = replaceName(fileName, stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }

        if (fileName.startsWith("-") || fileName.endsWith("-")) {
            fileName = replaceName(fileName, "-", EMPTY_STRING);
        }

        if (fileName.contains("   ")) {
            fileName = replaceName(fileName, "   ", EMPTY_STRING);
        }

        return fileName.replaceAll(" ", "");
    }


    private String replaceName(String fileName, String strKey, String strValue) {
        fileName = fileName.replace(strKey, strValue);
        return fileName.trim();
    }


    /**
     * 通过文件路径直接修改文件名
     *
     * @param filePath    需要修改的文件的完整路径
     * @param newFileName 需要修改的文件的名称
     * @return
     */
    private String fixFileName(String filePath, String newFileName) {
        File f = new File(filePath);
        // 判断原文件是否存在（防止文件名冲突）
        if (!f.exists()) {
            return null;
        }
        newFileName = newFileName.trim();

        // 文件名不能为空
        if ("".equals(newFileName)) {
            return null;
        }
        String substring = newFileName.substring(newFileName.lastIndexOf("."));
        if (!substring.isEmpty()) {
            String replace = substring.replace(".", "");
            if (CONSTANT_FILE_TYPE.contains(replace)) {
                newFileName = newFileName.replace(substring, "");
            }
        }
        String newFilePath = null;
        // 判断是否为文件夹
        if (f.isDirectory()) {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName
                    + filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
        }
        File nf = new File(newFilePath);
        try {
            // 修改文件名
            f.renameTo(nf);
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }
}
