package com.java.tutorial.project.mapper;

import com.java.tutorial.project.domain.mysql.CrmContactStatisticsEntity;
import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@UseDataSource("talent_rw")
public interface CrmContactStatisticsMapper extends BaseMapper<CrmContactStatisticsEntity> {
}
