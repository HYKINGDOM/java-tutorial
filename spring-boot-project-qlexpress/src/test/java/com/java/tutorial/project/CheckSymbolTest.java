package com.java.tutorial.project;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.java.tutorial.project.util.RuleConditionDTO;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void contextLoads_01() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a + b * c + a";
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("8", r.toString(), r.toString());
    }

    @Test
    public void contextLoads_03() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("b", 2);
        context.put("c", 3);
        context.put("a", 5);
        String express = "(a == 1) || ( b = 2) && ( c = 3 ) ";
        // 会抛出异常 com.ql.util.express.exception.QLException: run QlExpress Exception at line 1 :指令错误:2 不是Boolean
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("true", r.toString(), r.toString());
    }

    @Test
    public void contextLoads_04() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "(a == 1) || ( b = 2) && ( c = 3 ) ";
        //执行正常
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("true", r.toString(), r.toString());
    }

    @Test
    public void contextLoads_04_01() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "(a == 1) || ( b == 2) && ( c == 3 ) ";
        //执行正常
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("true", r.toString(), r.toString());
    }

    @Test
    public void contextLoads_05() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "(a == 5) || ( b = 2) && ( c = 3 ) ";
        //执行异常 com.ql.util.express.exception.QLException: run QlExpress Exception at line 1 :指令错误:2 不是Boolean
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("true", r.toString(), r.toString());
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
    public void contextLoads_test06() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", true);
        context.put("b", false);
        context.put("c", true);
        String express = "a || b && c";
        Object r = runner.execute(express, context, null, true, false);
        assertEquals("true", r.toString(), r.toString());
    }

    @Test
    public void contextLoads_test03() throws Exception {

        String param =
            "[{\"rule\":\"未接通\",\"symbol\":\"<\",\"value\":\"5\"},{\"rule\":\"已授权\",\"symbol\":null,\"value\":null},{\"rule\":\"未加微\",\"symbol\":null,\"value\":null}]";

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

    @Test
    public void contextLoads_test04() throws Exception {
        String json =
            "[{\"nestedConditions\":[{\"condition\":\"&&\",\"rule\":\"授权状态\",\"symbol\":\"=\",\"value\":\"未授权\"},{\"condition\":\"&&\",\"rule\":\"加微状态\",\"symbol\":\"=\",\"value\":\"已加微\"}]},{\"condition\":\"&&\"},{\"nestedConditions\":[{\"condition\":\"||\",\"rule\":\"历史通话记录\",\"symbol\":\"=\",\"value\":3},{\"condition\":\"||\",\"rule\":\"加微状态\",\"symbol\":\"=\",\"value\":\"未加微\"}]},{\"condition\":\"||\"},{\"nestedConditions\":[{\"condition\":\"&&\",\"rule\":\"历史通话记录\",\"symbol\":\"=\",\"value\":1},{\"condition\":\"&&\",\"rule\":\"授权状态\",\"symbol\":\"=\",\"value\":\"已授权\"}]}]";

        List<RuleConditionDTO> ruleConditionDTO = JSON.parseArray(json, RuleConditionDTO.class);

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder condition = new StringBuilder();

        Table<String, RuleConditionDTO, Boolean> hashBasedTable = HashBasedTable.create();
        Set<RuleConditionDTO> result = new HashSet<>();
        for (RuleConditionDTO conditionDTO : ruleConditionDTO) {

            if (CollectionUtil.isEmpty(conditionDTO.getNestedConditions())) {
                stringBuilder.append(conditionDTO.getCondition());
            } else {
                stringBuilder.append("(");
                List<RuleConditionDTO> nestedConditions = conditionDTO.getNestedConditions();
                int size = nestedConditions.size();
                for (int i = 0; i < size; i++) {

                    RuleConditionDTO dto = nestedConditions.get(i);
                    condition.append(dto.getRule());

                    String symbol = dto.getSymbol();

                    condition.append(dto.getSymbol());

//                    if ("=".equals(symbol)){
//                        condition.append("等于");
//                    }else {
//                        condition.append(dto.getSymbol());
//                    }
                    condition.append(dto.getValue());
                    hashBasedTable.put(condition.toString(), dto, false);

                    stringBuilder.append(condition);

                    if (size > 1 && i != size - 1) {
                        stringBuilder.append(dto.getCondition());
                    }

                    condition.delete(0, condition.length());
                    result.add(dto);
                }
                stringBuilder.append(")");
            }
        }

        System.out.println(hashBasedTable.rowKeySet());
        System.out.println(hashBasedTable.columnKeySet());
        System.out.println(stringBuilder);
        System.out.println(ruleConditionDTO);



        String param = stringBuilder.toString();
        DefaultContext<String, Object> context = new DefaultContext<>();
        for (String string : hashBasedTable.rowKeySet()) {
            context.put(string, false);
        }

        List<String> errorList = new ArrayList<>();
        Object r = runner.execute(param, context, errorList, true, false);
        System.out.println(errorList);
        assertEquals("false", r.toString(), r.toString());

    }


    @Test
    public void contextLoads_test05() throws Exception {
        String json =
            "[{\"nestedConditions\":[{\"condition\":\"&&\",\"rule\":\"授权状态\",\"symbol\":\"=\",\"value\":\"未授权\"},{\"condition\":\"&&\",\"rule\":\"加微状态\",\"symbol\":\"=\",\"value\":\"已加微\"}]},{\"condition\":\"&&\"},{\"nestedConditions\":[{\"condition\":\"||\",\"rule\":\"历史通话记录\",\"symbol\":\"=\",\"value\":3},{\"condition\":\"||\",\"rule\":\"加微状态\",\"symbol\":\"=\",\"value\":\"未加微\"}]},{\"condition\":\"||\"},{\"nestedConditions\":[{\"condition\":\"&&\",\"rule\":\"历史通话记录\",\"symbol\":\"=\",\"value\":5},{\"condition\":\"&&\",\"rule\":\"授权状态\",\"symbol\":\"=\",\"value\":\"已授权\"}]}]";

        List<RuleConditionDTO> ruleConditionDTO = JSON.parseArray(json, RuleConditionDTO.class);

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder condition = new StringBuilder();

        Table<String, RuleConditionDTO, Boolean> hashBasedTable = HashBasedTable.create();
        Set<String> result = new HashSet<>();
        for (RuleConditionDTO conditionDTO : ruleConditionDTO) {

            if (CollectionUtil.isEmpty(conditionDTO.getNestedConditions())) {
                stringBuilder.append(conditionDTO.getCondition());
            } else {
                stringBuilder.append(" (");
                List<RuleConditionDTO> nestedConditions = conditionDTO.getNestedConditions();
                int size = nestedConditions.size();
                for (int i = 0; i < size; i++) {

                    RuleConditionDTO dto = nestedConditions.get(i);
                    String rule = dto.getRule();
                    condition.append(rule);
                    String symbol = dto.getSymbol();
                    if ("=".equals(symbol)){
                        condition.append("==");
                    }else {
                        condition.append(dto.getSymbol());
                    }

                    condition.append(dto.getValue());
                    hashBasedTable.put(rule, dto, false);

                    stringBuilder.append(" (");
                    stringBuilder.append(condition);
                    stringBuilder.append(") ");
                    if (size > 1 && i != size - 1) {
                        stringBuilder.append(dto.getCondition());
                    }

                    condition.delete(0, condition.length());
                    result.add(rule);
                }
                stringBuilder.append(") ");
            }
        }

        System.out.println(hashBasedTable.rowKeySet());
        System.out.println(stringBuilder);

        String param = stringBuilder.toString();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("授权状态", "未授权");
        context.put("加微状态", "已加微");
        context.put("历史通话记录", 3);
        System.out.println(context);

        List<String> errorList = new ArrayList<>();
        Object r = runner.execute(param, context, errorList, false, true);

        assertEquals("false", r.toString(), r.toString());

    }


    public boolean checkSymbol(String symbol, Integer value, Integer count) {
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
