package com.java.tutorial.project.infrastructure.dao.area;

import com.java.tutorial.project.domain.persistantobject.area.AreaPO;
import org.beetl.sql.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 *
 * 行政区划表
 *
 *
 */
@Repository
public interface AreaDao extends BaseMapper<AreaPO> {

}
