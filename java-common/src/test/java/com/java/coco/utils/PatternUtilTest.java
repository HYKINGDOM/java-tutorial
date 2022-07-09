package com.java.coco.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatternUtilTest {


    private PatternUtil patternUtil;

    @Before
    public void setUp() throws Exception {
        patternUtil = new PatternUtil();
    }

    @Test
    public void TEST_IS_ONLY_CONTAINS_NUM_AND_LETTER_01() {
        String str = "XRW-353 ";
        boolean onlyCONTAINSNUMANDLETTER = patternUtil.isOnlyCONTAINSNUMANDLETTER(str);
        assertTrue(onlyCONTAINSNUMANDLETTER);
    }

    @Test
    public void TEST_IS_ONLY_CONTAINS_NUM_AND_LETTER_02(){
        String str = "XRW-353 完";
        boolean onlyCONTAINSNUMANDLETTER = patternUtil.isOnlyCONTAINSNUMANDLETTER(str);
        assertFalse(onlyCONTAINSNUMANDLETTER);
    }
}