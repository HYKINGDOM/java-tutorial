package com.java.tutorial.project.node;

import cn.hutool.core.collection.ListUtil;
import com.java.tutorial.project.domain.EmployeeInfo;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeIteratorComponent;

import java.util.Iterator;
import java.util.List;

/**
 * @author meta
 */
@LiteflowComponent("list")
public class ListComponent extends NodeIteratorComponent {

    @Override
    public Iterator<?> processIterator() {
        EmployeeInfo jack = EmployeeInfo.builder().name("jack").age(21).build();
        EmployeeInfo mary = EmployeeInfo.builder().name("mary").age(41).build();
        EmployeeInfo tom = EmployeeInfo.builder().name("tom").age(36).build();

        List<EmployeeInfo> list = ListUtil.toList(jack, mary, tom);
        return list.iterator();
    }

}