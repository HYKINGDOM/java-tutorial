package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.util.PageDomain;
import com.java.tutorial.project.util.SqlUtil;
import com.java.tutorial.project.util.TableSupport;
import com.java.tutorial.project.service.IBaseService;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * 自定义的服务基类接口实现
 *
 * @author dataprince数据小王子
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    /**
     * 构造基本查询条件
     *
     * @return QueryWrapper
     */
    protected QueryWrapper buildBaseQueryWrapper() {
        QueryWrapper queryWrapper = query();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            queryWrapper.orderBy(orderBy);
        }
        return queryWrapper;
    }
}
