package com.java.coco.BIExtend.service;

import cn.hutool.core.bean.BeanUtil;
import com.java.coco.BIExtend.domain.ParentDomain;
import com.java.coco.BIExtend.entity.EntityA;
import com.java.coco.BIExtend.entity.EntityB;
import com.java.coco.BIExtend.entity.EntityC;
import com.java.coco.BIExtend.entity.EntityD;
import com.java.coco.BIExtend.entity.EntityE;
import com.java.coco.BIExtend.entity.ParentEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            BeanUtil.copyProperties(parentDomain, parentEntity);
            parentEntities.add(parentEntity);
        }

        return parentEntities;
    }

    public List<ParentEntity> convertDomainToEntity(List<ParentDomain> parentDomainList) {
        List<ParentEntity> parentEntities = new ArrayList<>();
        for (ParentDomain parentDomain : parentDomainList) {
            ParentEntity parentEntity = parentEntityMap.get(parentDomain.typeCode());
            BeanUtil.copyProperties(parentDomain, parentEntity);
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
