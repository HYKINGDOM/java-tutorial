package com.java.coco.bookmarksyntool;

import java.util.List;

/**
 * @author meta
 */
public class ReadChromeBookMark {

    public List<WebUrl> readChromeBookMarkUtil() {
        String chromeBookMark = "C:\\Users\\HY\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Bookmarks";
        BookMarkUtils bookMarkUtils = new BookMarkUtils();
        return bookMarkUtils.BookMarkReadUtil(chromeBookMark);
    }

}
