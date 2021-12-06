package com.java.util.javautil.bookmarksyntool;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReadBookMarkTest {


    private ReadChromeBookMark readChromeBookMark;

    private ReadEdgeBookMark readEdgeBookMark;

    @Before
    public void init_class(){
        readChromeBookMark = new ReadChromeBookMark();
        readEdgeBookMark = new ReadEdgeBookMark();
    }

    @Test
    public void test_read_chrome_book_mark_01(){
        readChromeBookMark.readChromeBookMarkUtil();
    }

    @Test
    public void test_read_edge_book_01(){
        readEdgeBookMark.readEdgeBookMarkUtil();
    }

    @Test
    public void test_combine_bookmarks(){
        readChromeBookMark.readChromeBookMarkUtil();
        readEdgeBookMark.readEdgeBookMarkUtil();
    }

}