package com.java.tutorial.project.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.tutorial.project.domain.UserDO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * EasyExcel导入导出
 *
 * @author hy
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @GetMapping("/export/user")
    public void exportUserExcel(HttpServletResponse response) {
        try {
            this.setExcelResponseProp(response, "用户列表");
            List<UserDO> userList = this.getUserList();
            EasyExcel.write(response.getOutputStream()).head(UserDO.class).excelType(ExcelTypeEnum.XLSX)
                .sheet("用户列表").doWrite(userList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置响应结果
     *
     * @param response    响应结果对象
     * @param rawFileName 文件名
     * @throws UnsupportedEncodingException 不支持编码异常
     */
    private void setExcelResponseProp(HttpServletResponse response, String rawFileName)
        throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileName = URLEncoder.encode(rawFileName, StandardCharsets.UTF_8).replaceAll("\n+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }

    /**
     * 读取用户列表数据
     *
     * @return 用户列表数据
     * @throws IOException IO异常
     */
    private List<UserDO> getUserList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource classPathResource = new ClassPathResource("mock/users.json");
        InputStream inputStream = classPathResource.getInputStream();
        return objectMapper.readValue(inputStream, new TypeReference<>() {
        });
    }
}
