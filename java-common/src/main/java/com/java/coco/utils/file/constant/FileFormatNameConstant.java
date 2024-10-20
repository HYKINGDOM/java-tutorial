package com.java.coco.utils.file.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 格式化特殊文件名
 *
 * @author meta
 */
public class FileFormatNameConstant {

    static final List<String> FILTER_STRING_LIST =
        List.of("soushu555", "soushu2021", "2048社区-big2048", "thzu", "2048社区", "big2048", "bt-btt", "ai高清2k修复",
            "bbs.yzkof", "uuc82", "1024核工厂", "fun2048", "thzutm", "原创", "重磅泄露", "最新", "更新", "nyap2p",
            "bt-btt", "soav", "uncensored", "leaked", "每日更新", "每日", "799dvd", "606dvd", "136dvd", "136dvd",
            "909dvd", "132dvd", "fulibus", "91视频", "91自拍", "国产自拍", "kiki", "魔性论坛", "www.mox.life",
            "2048论坛bbs2048", "sexflexvideo", "yonitale", "bbs2048", "", "onlyfans", "eapk", "guochan2048", "8899xx",
            "jav20s8", "guochan2048", "soushu2021", "soushu2022", "搜书吧", "网址");
    private static final List<String> FILE_CHARACTER_LIST =
        List.of(" ", "-", "@", "「", "」", "《", "》", "【", "】", "[", "]", "『", "』", "〖", "〗", "▌", "(", ")", "（", "）", "。",
            "“", "”", "，", "~", "#", "!", "！", "+", "=", "⊙", "❤");
    private static final List<String> FILE_SPECIAL_SYMBOL_LIST =
        List.of(".net", ".org", ".com", ".cc", ".hd", ".org", ".xyz", "www.");

    public static List<String> getAllFileSymbolFormatName() {
        List<String> list = new ArrayList<>();
        list.addAll(FILE_CHARACTER_LIST);

        //特殊字符的大小写
        list.addAll(FILE_SPECIAL_SYMBOL_LIST);
        list.addAll(symbolToUpperCase(FILE_SPECIAL_SYMBOL_LIST));

        //特殊字符串的大小写
        list.addAll(FILTER_STRING_LIST);
        list.addAll(symbolToUpperCase(FILTER_STRING_LIST));

        return list.stream().distinct().sorted().collect(Collectors.toList());
    }

    private static List<String> symbolToUpperCase(List<String> originList) {
        List<String> list = new ArrayList<>();
        for (String str : originList) {
            list.add(str.toUpperCase());
        }
        return list;
    }
}
