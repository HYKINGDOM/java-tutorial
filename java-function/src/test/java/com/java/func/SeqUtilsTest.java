package com.java.func;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeqUtilsTest {


    @Test
    public void test_Function() {

        String str = "Ae_DC_Yg_aaaa_oooo";

        String underscoreToCamel = SeqUtils.underscoreToCamel(str);

        Assertions.assertEquals("aeDcYgAaaaOooo", underscoreToCamel);
    }

}