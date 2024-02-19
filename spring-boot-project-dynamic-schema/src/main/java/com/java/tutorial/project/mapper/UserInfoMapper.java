package com.java.tutorial.project.mapper;

import com.java.tutorial.project.domain.schema.UserInfo;
import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@UseDataSource("postgres_rw")
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
