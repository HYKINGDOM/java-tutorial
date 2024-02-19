package com.java.tutorial.project;

import com.java.tutorial.project.domain.People;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootProjectApplicationTests {
    @Autowired
    private KieSession session;

    @Autowired
    private KieBase kieBase;

    @Test
    public void people() {

        People people = new People();
        people.setName("baolan");
        people.setSex(1);
        people.setDrlType("people");
        session.insert(people);//插入
        session.fireAllRules();//执行规则
        int fireAllRules = session.fireAllRules();
        System.out.println(fireAllRules);
    }

    @AfterEach
    public void runDispose() {
        session.dispose();//释放资源
    }

}
