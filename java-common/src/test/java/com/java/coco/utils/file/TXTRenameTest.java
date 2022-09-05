package com.java.coco.utils.file;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TXTRenameTest {

    private TXTRename txtRename;


    @BeforeEach
    public void init_class(){
        txtRename = new TXTRename();
    }

    @Test
    public void test_rename_01(){
        File file = new File("J:\\小说");
        TXTRename txtRename = new TXTRename();
        txtRename.txtRenamed(file);
    }

}