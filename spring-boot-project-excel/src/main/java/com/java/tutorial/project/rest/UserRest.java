package com.java.tutorial.project.rest;

import com.alibaba.excel.EasyExcelFactory;
import com.zhy.easyexceldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author meta
 */
@RestController
@RequestMapping("/user")
public class UserRest extends BaseRest {

    @Autowired
    private UserService userService;

    /**
     * easyexcel通过validation和正则实现excel导入校验
     * 导入表头校验
     * 导入数据校验
     * 导入业务逻辑校验
     */
    @PostMapping("/importExcel")
    public Result importExcel(HttpServletResponse response, @RequestParam MultipartFile file) throws IOException {
        EasyExcelListener easyExcelListener = new EasyExcelListener(userService, UserExcelDto.class);
        EasyExcelFactory.read(file.getInputStream(), UserExcelDto.class, easyExcelListener).sheet().doRead();
        List<ExcelCheckErrDto<UserExcelDto>> errList = easyExcelListener.getErrList();
        if (!errList.isEmpty()) {
            //如果包含错误信息就导出错误信息
            exportErrorMsg(response, errList);
        }
        return addSucResult();
    }

    private static void exportErrorMsg(HttpServletResponse response, List<ExcelCheckErrDto<UserExcelDto>> errList) throws IOException {
        List<UserExcelErrDto> excelErrDtos = errList.stream().map(excelCheckErrDto -> {
            UserExcelErrDto userExcelErrDto = JSON.parseObject(JSON.toJSONString(excelCheckErrDto.getT()), UserExcelErrDto.class);
            userExcelErrDto.setErrMsg(excelCheckErrDto.getErrMsg());
            return userExcelErrDto;
        }).collect(Collectors.toList());
        EasyExcelUtils.webWriteExcel(response, excelErrDtos, UserExcelErrDto.class, "用户导入错误信息");
    }
}
