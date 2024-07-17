package com.java.tutorial.project.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import com.java.tutorial.project.domain.GenTable;
import com.java.tutorial.project.domain.GenTableColumn;
import com.java.tutorial.project.service.IGenTableColumnService;
import com.java.tutorial.project.service.IGenTableService;
import com.java.tutorial.project.util.R;
import com.java.tutorial.project.util.TableDataInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author meta
 * @author meta
 */
@Validated
@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {
    @Resource
    private IGenTableService genTableService;

    @Resource
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    public TableDataInfo<GenTable> genList(GenTable genTable) {
        return genTableService.selectPage(genTable);
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{tableId}")
    public R<Map<String, Object>> getInfo(@PathVariable Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<>(3);
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return R.ok(map);
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    public TableDataInfo dataList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableId}")
    public TableDataInfo<GenTableColumn> columnList(@PathVariable("tableId") Long tableId) {
        TableDataInfo<GenTableColumn> dataInfo = new TableDataInfo<>();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public R<Void> importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return R.ok();
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public R<Void> editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return R.ok();
    }

    /**
     * 删除代码生成
     */
    @DeleteMapping("/{tableIds}")
    public R<Void> remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return R.ok();
    }

    /**
     * 预览代码,前端界面类型frontType取值：0是element-js、1是element-ts、2是antdesign-ts
     */
    @GetMapping("/preview")
    public R<Map<String, String>> preview(Long tableId, Integer frontType) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId, frontType);
        return R.ok(dataMap);
    }

    //    /**
    //     * 生成代码（下载方式）
    //     */
    //    @SaCheckPermission("tool:gen:code")
    //    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    //    @GetMapping("/download/{tableId}")
    //    public void download(HttpServletResponse response, @PathVariable("tableId") Long tableId) throws IOException {
    //        byte[] data = genTableService.downloadCode(tableId);
    //        genCode(response, data);
    //    }

    /**
     * 生成代码（自定义路径）,前端界面类型frontType取值：0是element-js、1是element-ts、2是antdesign-ts
     */
    @GetMapping("/genCode")
    public R<Void> genCode(Long tableId, Integer frontType) {
        genTableService.generatorCode(tableId, frontType);
        return R.ok();
    }

    /**
     * 同步数据库
     */
    @GetMapping("/synchDb/{tableId}")
    public R<Void> synchDb(@PathVariable("tableId") Long tableId) {
        genTableService.synchDb(tableId);
        return R.ok();
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tableIdStr, Integer frontType) throws IOException {
        String[] tableIds = Convert.toStrArray(tableIdStr);
        byte[] data = genTableService.downloadCode(tableIds, frontType);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"meta-flex.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), false, data);
    }
}
