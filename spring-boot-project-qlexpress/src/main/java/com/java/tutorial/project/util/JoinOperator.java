package com.java.tutorial.project.util;

import com.ql.util.express.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个继承自com.ql.util.express.Operator的操作符
 */
public class JoinOperator extends Operator {

    @Override
    public Object executeInner(Object[] list) throws Exception {
        Object opdata1 = list[0];
        Object opdata2 = list[1];
        if (opdata1 instanceof List) {
            ((List)opdata1).add(opdata2);
            return opdata1;
        } else {
            List result = new ArrayList();
            for (Object opdata : list) {
                result.add(opdata);
            }
            return result;
        }
    }
}