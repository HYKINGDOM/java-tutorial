package com.java.tutorial.project.controller;

import com.java.tutorial.project.domain.SysJobLog;
import com.java.tutorial.project.service.ISysJobLogService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调度日志操作处理
 *
 * @author meta
 */
@RestController
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController {

    @Resource
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */

    @GetMapping("/list")
    public AjaxResult list(SysJobLog sysJobLog) {
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        return AjaxResult.success(list);
    }

    /**
     * 根据调度编号获取详细信息
     */

    @GetMapping(value = "/{jobLogId}")
    public AjaxResult getInfo(@PathVariable Long jobLogId) {
        return success(jobLogService.selectJobLogById(jobLogId));
    }

    /**
     * 删除定时任务调度日志
     */
    @DeleteMapping("/{jobLogIds}")
    public AjaxResult remove(@PathVariable Long[] jobLogIds) {
        return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
    }

    /**
     * 清空定时任务调度日志
     */
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        jobLogService.cleanJobLog();
        return success();
    }
}
