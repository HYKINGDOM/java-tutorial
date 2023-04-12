package com.java.func;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class SeqUtilsTest {




    @Test
    public void test_Function() {

        String str = "Ae_DC_Yg_aaaa_oooo";

        String underscoreToCamel = SeqUtils.underscoreToCamel(str);

        System.out.println(underscoreToCamel);

    }

}