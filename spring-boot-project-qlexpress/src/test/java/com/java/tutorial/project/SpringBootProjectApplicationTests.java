package com.java.tutorial.project;

import org.junit.jupiter.api.Test;

import com.java.tutorial.project.util.JoinOperator;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

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

}
