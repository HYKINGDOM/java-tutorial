package com.java.util.javautil.huawei;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LastStringLengthTest {

    private LastStringLength lastStringLength;

    @Before
    public void initTest(){
        lastStringLength = new LastStringLength();
    }

    @Test
    public void first_string(){
        String strLength = "hello nowcoder";
        int length = lastStringLength.calculationLastStringLength(strLength);
        assertEquals(8,length);
    }

    @Test
    public void second_string(){
        String strLength = "XSUWHQ";
        int length = lastStringLength.calculationLastStringLength(strLength);
        assertEquals(6,length);
    }

    @Test
    public void three_string(){
        String strLength = "88";
        int length = lastStringLength.calculationLastStringLength(strLength);
        assertEquals(6,length);
    }


}