package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.domain.GenTableColumn;
import com.java.tutorial.project.mapper.GenTableColumnMapper;
import com.java.tutorial.project.service.IGenTableColumnService;
import com.java.tutorial.project.util.Convert;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.java.tutorial.project.domain.table.GenTableColumnTableDef.GEN_TABLE_COLUMN;

/**
 * 业务字段 服务层实现
 *
 * @author meta
 * @author meta
 */
@Service
public class GenTableColumnServiceImpl extends BaseServiceImpl<GenTableColumnMapper, GenTableColumn>
    implements IGenTableColumnService {
    @Resource
    private GenTableColumnMapper genTableColumnMapper;

    @Override
    public QueryWrapper query() {
        return super.query().from(GEN_TABLE_COLUMN);
    }

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
        QueryWrapper queryWrapper =
            query().where(GEN_TABLE_COLUMN.TABLE_ID.eq(tableId)).orderBy(GEN_TABLE_COLUMN.SORT.asc());
        return this.list(queryWrapper);
    }

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int insertGenTableColumn(GenTableColumn genTableColumn) {
        Long loginUserId = 1L;
        Date createTime = new Date();
        genTableColumn.setCreateBy(loginUserId);
        genTableColumn.setCreateTime(createTime);
        genTableColumn.setUpdateBy(loginUserId);
        genTableColumn.setUpdateTime(createTime);

        return genTableColumnMapper.insertSelective(genTableColumn);
    }

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int updateGenTableColumn(GenTableColumn genTableColumn) {
        Date createTime = new Date();
        genTableColumn.setUpdateBy(1L);
        genTableColumn.setUpdateTime(createTime);

        return genTableColumnMapper.update(genTableColumn);
    }

    /**
     * 删除业务字段对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGenTableColumnByIds(String ids) {
        return genTableColumnMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
    }
}
