package com.java.util.javautil.BIExtend.service;

import com.java.util.javautil.BIExtend.domain.*;
import com.java.util.javautil.BIExtend.entity.EntityA;
import com.java.util.javautil.BIExtend.entity.EntityB;
import com.java.util.javautil.BIExtend.entity.EntityC;
import com.java.util.javautil.BIExtend.entity.ParentEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParentServiceTest {

    private ParentService parentService;

    private List<ParentDomain> parentDomainList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        parentService = new ParentService();
        DomainA domainA = DomainA.builder().age(12).build();
        domainA.setUserId(100);
        domainA.setUserName("domainA");

        DomainB domainB = new DomainB();
        domainB.setUserId(101);
        domainB.setUserName("domainB");

        DomainC domainC = new DomainC();
        domainC.setGender(true);
        domainC.setUserId(102);
        domainC.setUserName("domainC");

        DomainD domainD = new DomainD();
        domainD.setEmailAddress("123@qq.com");
        domainD.setUserId(103);
        domainD.setUserName("domainD");

        DomainE domainE = new DomainE();
        domainE.setPassword("password");
        domainE.setUserId(104);
        domainE.setUserName("domainE");

        parentDomainList.add(domainA);
        parentDomainList.add(domainB);
        parentDomainList.add(domainC);
        parentDomainList.add(domainD);
        parentDomainList.add(domainE);
    }

    @Test
    public void convertList() {
        List<? extends ParentEntity> parentEntities = parentService.convertList(parentDomainList);
        assertEquals(5,parentEntities.size());
        parentEntities.forEach(System.out::println);
    }

    @Test
    public void convert_domain_to_entity(){
        List<ParentEntity> parentEntities = parentService.convertDomainToEntity(parentDomainList);
        assertEquals(5,parentEntities.size());
        parentEntities.forEach(System.out::println);
    }


    @Test
    public void convert_domain_to_entity_by_enum(){
        List<ParentEntity> parentEntityList = parentService.convertDomainToEntityByEnum(parentDomainList);
        assertEquals(5,parentEntityList.size());
        parentEntityList.forEach(System.out::println);
    }

}