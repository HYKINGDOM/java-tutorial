package com.java.coco.bookmarksyntool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadBookMarkTest {

    private ReadChromeBookMark readChromeBookMark;

    private ReadEdgeBookMark readEdgeBookMark;

    @BeforeEach
    public void init_class() {
        readChromeBookMark = new ReadChromeBookMark();
        readEdgeBookMark = new ReadEdgeBookMark();
    }

    @Test
    public void test_read_chrome_book_mark_01() {
        readChromeBookMark.readChromeBookMarkUtil();
    }

    @Test
    public void test_read_edge_book_01() {
        readEdgeBookMark.readEdgeBookMarkUtil();
    }

    @Test
    public void test_combine_bookmarks() {
        readChromeBookMark.readChromeBookMarkUtil();
        readEdgeBookMark.readEdgeBookMarkUtil();
    }

}