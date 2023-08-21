package com.java.tutorial.project.application.service.area;

import java.time.LocalDateTime;

import com.java.tutorial.project.domain.businessobject.area.AreaCreateBO;
import com.java.tutorial.project.domain.businessobject.area.AreaModifyBO;
import com.java.tutorial.project.domain.persistantobject.area.AreaPO;
import com.java.tutorial.project.infrastructure.dao.area.AreaDao;
import io.github.linpeilie.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

/**
 *
 * 行政区划表
 *
 *
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AreaCmdSvc {

    private final AreaDao dao;
    private final Converter converter;

    /**
     * 保存
     *
     * @param bo
     */
    public void create(AreaCreateBO bo) {
        //LocalDateTime now = LocalDateTime.now();
        //Long userId = AccessContext.getAccessUser().getId();
        AreaPO po = converter.convert(bo, AreaPO.class);
        //po.setCreateBy(userId);
        //po.setCreateAt(now);
        //po.setUpdateBy(userId);
        //po.setUpdateAt(now);
        //po.setDel(0);
        dao.insertTemplate(po);
    }

    /**
     * 修改
     *
     * @param bo
     */
    public void modify(AreaModifyBO bo) {
        //LocalDateTime now = LocalDateTime.now();
        //Long userId = AccessContext.getAccessUser().getId();
        AreaPO po = converter.convert(bo, AreaPO.class);
        //po.setUpdateBy(userId);
        //po.setUpdateAt(now);
        dao.updateTemplateById(po);
    }

}
