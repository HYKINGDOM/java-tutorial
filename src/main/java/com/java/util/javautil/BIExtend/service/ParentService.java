package com.java.util.javautil.BIExtend.service;

import com.java.util.javautil.BIExtend.domain.ParentDomain;
import com.java.util.javautil.BIExtend.entity.*;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class ParentService {

    private static Map<Integer, ParentEntity> parentEntityMap = new HashMap<>();

    static {
        parentEntityMap.put(1, new EntityA());
        parentEntityMap.put(2, new EntityB());
        parentEntityMap.put(3, new EntityC());
        parentEntityMap.put(4, new EntityD());
        parentEntityMap.put(5, new EntityE());
    }


    public List<? extends ParentEntity> convertList(List<ParentDomain> parentDomainList) {

        List<ParentEntity> parentEntities = new ArrayList<>();
        for (ParentDomain parentDomain : parentDomainList) {
            ParentEntity parentEntity = parentEntityMap.get(parentDomain.typeCode());
            BeanUtils.copyProperties(parentDomain, parentEntity);
            parentEntities.add(parentEntity);
        }

        return parentEntities;
    }


    public List<ParentEntity> convertDomainToEntity(List<ParentDomain> parentDomainList) {
        List<ParentEntity> parentEntities = new ArrayList<>();
        for (ParentDomain parentDomain : parentDomainList) {
            ParentEntity parentEntity = parentEntityMap.get(parentDomain.typeCode());
            BeanUtils.copyProperties(parentDomain, parentEntity);
            parentEntities.add(parentEntity);
        }

        return parentEntities;
    }

    public List<ParentEntity> convertDomainToEntityByEnum(List<ParentDomain> parentDomainList) {
        List<ParentEntity> parentEntities = new ArrayList<>();
//        for (ParentDomain parentDomain : parentDomainList) {
//            ParentEntity entity = DomainEnum.getEntity(parentDomain.typeCode());
//            BeanUtils.copyProperties(parentDomain, Objects.requireNonNull(entity));
//            parentEntities.add(entity);
//        }
        return parentEntities;
    }
}
