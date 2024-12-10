package com.java.tutorial.project.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.java.tutorial.project.domain.OrderItem;
import com.java.tutorial.project.domain.Page;
import com.java.tutorial.project.domain.PageRequest;
import com.java.tutorial.project.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author kscs
 */
@Slf4j
@RestController
@RequestMapping("/orders")
public class OptimizedOrderItemController {

    @Resource
    private OrderItemService orderItemService;

    /**
     * 处理订单导出请求，使用StreamingResponseBody实现流式响应。 这样做可以确保即使有大量的订单数据也不会导致内存溢出， 同时可以让用户更快地开始接收文件。
     */
    @GetMapping("/export")
    public ResponseEntity<StreamingResponseBody> exportOrders() {
        StreamingResponseBody stream = outputStream -> {
            int pageSize = 1000; // 每页查询的数量
            int pageNumber = 0;  // 分页起始页码
            boolean hasMoreData = true; // 是否还有更多数据标志

            // 创建 ExcelWriter
            ExcelWriter excelWriter = EasyExcel.write(outputStream, OrderItem.class).build();

            // 创建 Sheet
            WriteSheet writeSheet = EasyExcel.writerSheet("test").build();

            try {
                // 循环直到所有数据都被处理完毕
                while (hasMoreData) {
                    // 构造分页请求参数
                    PageRequest pageRequest = PageRequest.of(pageNumber++, pageSize, "id");
                    // 执行分页查询
                    Page<OrderItem> page = orderItemService.findPage(pageRequest);
                    // 遍历当前页的数据，并逐行写入输出流
                    writeOrderToExcelRow(page.getContent(), OrderItem.class, excelWriter, writeSheet);
                    // 更新是否有更多数据的标志
                    hasMoreData = page.isHasNext();
                }
            } catch (IOException e) {
                // 如果发生IO异常，记录日志或采取其他措施
                log.error("Error writing to output stream: ", e);
            } finally {
                // 关闭 ExcelWriter
                if (excelWriter != null) {
                    excelWriter.finish();
                }
            }
        };

        // 设置HTTP头信息，告知浏览器这是一个附件下载
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders.xlsx");

        // 返回包含StreamingResponseBody的ResponseEntity对象
        return ResponseEntity.ok().headers(headers)
            // 设置响应的内容类型为二进制流
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            // 将StreamingResponseBody作为响应体
            .body(stream);
    }

    /**
     * 将单个订单转换成一行Excel格式，并写入到提供的输出流中。 注意：这里简化了实际的Excel写入逻辑，具体实现依赖于所使用的库（如Apache POI）。
     */
    private void writeOrderToExcelRow(List<OrderItem> objects, Class<OrderItem> clazz, ExcelWriter excelWriter, WriteSheet writeSheet) throws IOException {
        if (objects == null || objects.isEmpty()) {
            return;
        }

        // 写入数据
        excelWriter.write(objects, writeSheet);
    }


}
