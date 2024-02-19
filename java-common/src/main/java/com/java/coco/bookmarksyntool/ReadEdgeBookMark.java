package com.java.coco.bookmarksyntool;

import java.util.List;

/**
 * @author HY
 */
public class ReadEdgeBookMark {

    public List<WebUrl> readEdgeBookMarkUtil() {
        String chromeBookMark = "C:\\Users\\HY\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\Bookmarks";
        BookMarkUtils bookMarkUtils = new BookMarkUtils();
        return bookMarkUtils.BookMarkReadUtil(chromeBookMark);
    }
}
