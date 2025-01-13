package com.java.tutorial.project;

import com.alibaba.fastjson2.JSONArray;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckSymbolTest {


    private final static ExpressRunner runner = new ExpressRunner();

    @Test
    public void contextLoads() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a + b * c";
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("7", r.toString(), r.toString());
    }


    @Test
    public void contextLoads_test01() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a + b * c";
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("7", r.toString(), r.toString());
    }

    @Test
    public void contextLoads_test03() throws Exception {

        String param = "[{\"rule\":\"未接通\",\"symbol\":\"<\",\"value\":\"5\"},{\"rule\":\"已授权\",\"symbol\":null,\"value\":null},{\"rule\":\"未加微\",\"symbol\":null,\"value\":null}]";

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("未接通", 1);
        String express = "(未接通 < 5)";
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("true", r.toString(), r.toString());
    }


    @Test
    public void contextLoads_test02() throws Exception {
        String param = " (true || false) && true || (true || false)";
        DefaultContext<String, Object> context = new DefaultContext<>();
        Object r = runner.execute(param, context, null, true, false);
        assertEquals("true", r.toString(), r.toString());
    }



    public static boolean checkSymbol(String symbol, Integer value, Integer count) {
        switch (symbol) {
            case "<":
                return count < value;
            case "<=":
                return count <= value;
            case ">":
                return count > value;
            case ">=":
                return count >= value;
            case "=":
                return count.equals(value);
            default:
                return false;
        }
    }

}
