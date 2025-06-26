package com.java.tutorial.project;

import org.junit.jupiter.api.Test;

import com.java.tutorial.project.util.JoinOperator;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SpringBootProjectApplicationTests {

    @Test
    void contextLoads() throws Exception {

        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a + b * c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }

    @Test
    void contextLoads_01() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);

        String express = "如果 (语文 + 数学 + 英语 > 270) 则 {return 1;} 否则 {return 0;}";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        Object execute = runner.execute(express, context, null, false, false, 100);
        System.out.println(execute);
    }

    @Test
    void contextLoads_otc_01() throws Exception {
        ExpressRunner runner = new ExpressRunner();

        runner.addOperatorWithAlias("大于", ">", null);
        runner.addOperatorWithAlias("小于", "<",  null);
        runner.addOperatorWithAlias("且", "&&",  null);
        runner.addOperatorWithAlias("或", "||", null);

        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("roi", new BigDecimal(1.6));
        context.put("gsv", new BigDecimal(2100000));
        context.put("live_month", 1);

        String express = "roi大于1.5";

        List<String> errorList = new ArrayList<>();

        Object execute = runner.execute(express, context, errorList, false, false, 100);
        System.out.println(execute);
        System.out.println(errorList);
    }


    @Test
    void contextLoads_otc_01_fixed() throws Exception {
        ExpressRunner runner = new ExpressRunner();

        // 移除中文别名配置，直接使用标准操作符
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("roi", 1.6);  // 使用double类型替代BigDecimal
        context.put("gsv", 2100000.0);  // 使用double类型替代BigDecimal
        context.put("live_month", 1);

        // 修改表达式为标准语法格式
        String express = "roi > 1.5 && gsv > 2800000";

        List<String> errorList = new ArrayList<>();

        Object execute = runner.execute(express, context, errorList, false, false, 100);
        System.out.println(execute);
        System.out.println(errorList);
    }


    @Test
    void contextLoads_02() throws Exception {
        // (1)addOperator
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        runner.addOperator("join", new JoinOperator());
        Object r = runner.execute("1 join 2 join 3", context, null, false, false);
        System.out.println(r); // 返回结果 [1, 2, 3]

    }

    @Test
    void contextLoads_04() throws Exception {

        // (2)replaceOperator
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        runner.replaceOperator("+", new JoinOperator());
        Object r = runner.execute("1 + 2 + 3", context, null, false, false);
        System.out.println(r); // 返回结果 [1, 2, 3]
    }

    @Test
    void contextLoads_05() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        runner.addFunction("join", new JoinOperator());
        Object r = runner.execute("join(1, 2, 3)", context, null, false, false);
        System.out.println(r); // 返回结果 [1, 2, 3]
    }


    @Test
    void contextLoads_06() throws Exception {

        // (2)replaceOperator
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        runner.replaceOperator("+", new JoinOperator());
        Object r = runner.execute("1 + 2 + 3", context, null, false, false);
        System.out.println(r); // 返回结果 [1, 2, 3]
    }
}
