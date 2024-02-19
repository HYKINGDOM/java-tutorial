package com.java.tutorial.project.mapper;

import com.java.tutorial.project.domain.TalentSchema;
import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@UseDataSource("postgres_rw")
public interface TalentSchemaMapper extends BaseMapper<TalentSchema> {
}
