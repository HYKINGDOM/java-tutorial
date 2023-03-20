package com.java.coco.utils;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.ReUtil;
import org.junit.Before;
import org.junit.Test;

import static com.java.coco.utils.PatternUtil.MOR_CONTAINS_HA;
import static org.junit.Assert.*;

public class PatternUtilTest {


    private PatternUtil patternUtil;

    @Before
    public void setUp(){
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

    @Test
    public void TEST_MOR_CONTAINS_HA_01(){

        String str = "哈哈";
        boolean match = ReUtil.isMatch(MOR_CONTAINS_HA, str);
        assertTrue(match);
    }

    @Test
    public void TEST_MOR_CONTAINS_HA_01_01(){

        String str = "哈";
        boolean match = ReUtil.isMatch(MOR_CONTAINS_HA, str);
        assertTrue(match);
    }

    @Test
    public void TEST_MOR_CONTAINS_HA_01_02(){

        String str = "哈哈哈";
        boolean match = ReUtil.isMatch(MOR_CONTAINS_HA, str);
        assertTrue(match);
    }

    @Test
    public void TEST_MOR_CONTAINS_HA_01_03(){

        String str = "哈哈哈";
        boolean match = ReUtil.isMatch(MOR_CONTAINS_HA, str);
        assertTrue(match);
    }


    @Test
    public void TEST_MOR_CONTAINS_HA_02(){

        String str = "直接失业了";
        boolean match = ReUtil.isMatch(MOR_CONTAINS_HA, str);
        assertFalse(match);
    }

    @Test
    public void TEST_MOR_CONTAINS_HA_03(){

        String str = "直接失业了哈";
        boolean match = ReUtil.isMatch(MOR_CONTAINS_HA, str);
        assertTrue(match);
    }



    @Test
    public void TEST_MOR_CONTAINS_HA(){

        String str = "好";
        System.out.println(UnicodeUtil.toUnicode(str));
    }
}