package com.java.tutorial.project.rule;

import cn.hutool.core.util.StrUtil;
import com.java.tutorial.project.domain.People;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.definitions.rule.impl.RuleImpl;

/**
 * @author HY
 */
@Slf4j
public class PeopleRuleAction {

    public static void doParse(People people, RuleImpl rule) {
        String format = StrUtil.format("{} is matched Rule[{}]!", people, rule.getName());
        System.out.println(format);
    }
}
