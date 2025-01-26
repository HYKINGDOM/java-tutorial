package com.java.tutorial.project.util;

import cn.hutool.core.util.ZipUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 * @author kscs
 */
@Slf4j
@Service
public class ExportExcel {

    @Resource
    private CacheService cacheService;

    @Resource
    private CommonThreadManage commonThreadManage;

    private static final long SYS_REDIS_EXPIRE_TIME = 30;
    //    private static final int ROW_SIZE = 100000;
    //    private static final int ROW_PAGE = 10000;

    private static final int ROW_SIZE = 10000;
    private static final int ROW_PAGE = 10;

    public String exportTable(ExportTable exportTable) {
        StringBuffer path = new StringBuffer();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringBuffer sign = new StringBuffer();
        // redis key
        sign.append(exportTable.getId());
        try {
            String fileName = exportTable.getFileName();
            int rowCount = exportTable.getRowCount();
            List<List<String>> head = exportTable.getHead();
            Set<Integer> mergeIndex = exportTable.getMergeIndex();

            List<ExportTable.ExportColumn> fields = exportTable.getFields();
            // 用来记录需要为 行 列设置样式
            Map<String, Map<Integer, List<Map<Integer, ExportTable.ExportColumn.Font>>>> map = new HashMap<>();
            sign.append("#").append(fields.stream().map(e -> e.isShow()? "true" : "false").collect(Collectors.joining(",")));
            setFontStyle(0, exportTable.getFields(), map);
            // 获取表头长度
            int headRow = head.stream().max(Comparator.comparingInt(List::size)).get().size();

            // 数据量超过十万 则不带样式
            // 只处理表头：表头合并 表头隐藏 表头冻结
            if (rowCount * fields.size() > ROW_SIZE * 6.4) {
                map.put("cellStyle", null);
            }
            sign.append("#").append(exportTable.getStyle());
            // 数据量超过百万或者数据为空，只返回有表头得单元格
            if (rowCount == 0 || rowCount * fields.size() >= ROW_SIZE * 1800) {
                EasyExcel.write(outputStream)
                    // 这里放入动态头
                    .head(head).sheet("数据")
                    // 传入表头样式
                    .registerWriteHandler(EasyExcelUtils.getStyleStrategy())
                    // 当然这里数据也可以用 List<List<String>> 去传入
                    .doWrite(new LinkedList<>());
                byte[] bytes = outputStream.toByteArray();
                // 上传文件到FaS stDF 返回上传路径
                //                return fastWrapper.uploadFile(bytes, bytes.length, "xlsx") + "?filename=" + fileName + ".xlsx";
                return FileUtil.writeFileByBytes(fileName, bytes);
            }
            /**
             * 相同的下载文件请求 直接返回
             * the redis combines with datasetId - filter - size of data
             */
            if (cacheService.exists(sign.toString())) {
                return cacheService.get(sign.toString());
            }
            /**
             * 分sheet页
             * divide into sheets with 10M data per sheet
             */
            int sheetCount = (rowCount / (ROW_SIZE * ROW_PAGE)) + 1;
            String[] paths = new String[sheetCount];
            ByteArrayInputStream[] ins = new ByteArrayInputStream[sheetCount];

            // 自定义线程池
            Executor threadExecutor = commonThreadManage.asyncCommonExecutor();
            CountDownLatch threadSignal = new CountDownLatch(sheetCount);
            for (int i = 0; i < sheetCount; i++) {
                int finalI = i;
                threadExecutor.execute(() -> {
                    // excel文件流
                    ByteArrayOutputStream singleOutputStream = new ByteArrayOutputStream();
                    ExcelWriter excelWriter = EasyExcel.write(singleOutputStream).build();

                    // 单sheet页写入数
                    int sheetThreadCount = finalI == (sheetCount - 1)? (rowCount - finalI * (ROW_SIZE * ROW_PAGE)) / ROW_SIZE + 1 : ROW_PAGE;
                    CountDownLatch sheetThreadSignal = new CountDownLatch(sheetThreadCount);
                    for (int j = 0; j < sheetThreadCount; j++) {
                        int page = finalI * ROW_PAGE + j + 1;
                        // 最后一页数据
                        int pageSize = j == (sheetThreadCount - 1) && finalI == (sheetCount - 1)? rowCount % ROW_SIZE : ROW_SIZE;
                        threadExecutor.execute(() -> {
                            try {
                                writeExcel(page, pageSize, head, map, headRow, excelWriter, mergeIndex, finalI);
                                sheetThreadSignal.countDown();
                            } catch (Exception e) {
                                log.error("writeExcel error", e);
                            }
                        });
                    }
                    try {
                        sheetThreadSignal.await();
                    } catch (InterruptedException e) {
                        log.error("sheetThreadSignal error", e);
                    }
                    // 关闭写入流
                    excelWriter.finish();
                    paths[finalI] = (finalI + 1) + "-" + fileName + ".xlsx";
                    ins[finalI] = new ByteArrayInputStream(singleOutputStream.toByteArray());
                    // 单文件
                    if (sheetCount == 1) {
                        byte[] bytes = singleOutputStream.toByteArray();
                        try {
                            path.append(FileUtil.writeFileByBytes(fileName + ".xlsx",bytes));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        // 将sign存入redis并设置过期时间
                        cacheService.setEx(sign.toString(), path.toString(), SYS_REDIS_EXPIRE_TIME, TimeUnit.MINUTES);
                    }
                    threadSignal.countDown();
                });
            }
            threadSignal.await();

            if (sheetCount!= 1) {
                ZipUtil.zip(outputStream, paths, ins);
                byte[] bytes = outputStream.toByteArray();
                // 上传文件到FastDFS  返回上传路径
                //                path.append(fastWrapper.uploadFile(bytes, bytes.length, "zip"))
                //                        .append("?filename=").append(fileName).append(".zip");
                path.append(FileUtil.writeFileByBytes(fileName + ".zip",bytes));
                // 将sign存入redis并设置过期时间
                cacheService.setEx(sign.toString(), path.toString(), SYS_REDIS_EXPIRE_TIME, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            log.error("An error occurred during export: ", e);
        }
        return path.toString();
    }

    private void writeExcel(int page, int pageSize, List<List<String>> head,
        Map<String, Map<Integer, List<Map<Integer, ExportTable.ExportColumn.Font>>>> map, int headRow,
        ExcelWriter excelWriter, Set<Integer> mergeIndex, int sheetCount) {


        // todo 这里进行数据拼装

//        WriteSheet writeSheet = EasyExcel.writerSheet(sheetCount, "第" + (sheetCount + 1) + "页数据")
//            // 这里放入动态头
//            .head(head)
//            // 传入样式
//            .registerWriteHandler(EasyExcelUtils.getStyleStrategy())
//            .registerWriteHandler(new CellColorSheetWriteHandler(map, headRow))
//            .registerWriteHandler(new MergeStrategy(CollectionUtils.size(data), mergeIndex))
//            // 当然这里数据也可以用 List<List<String>> 去传入
//            .build();
//        excelWriter.write(data, writeSheet);
    }

    private void setFontStyle(int row, List<ExportTable.ExportColumn> fields,
        Map<String, Map<Integer, List<Map<Integer, ExportTable.ExportColumn.Font>>>> map) {
        Map<Integer, List<Map<Integer, ExportTable.ExportColumn.Font>>> rowStyle = new HashMap<>();
        List<Map<Integer, ExportTable.ExportColumn.Font>> columnStyles = new ArrayList<>();
        Map<Integer, ExportTable.ExportColumn.Font> columnStyle = new HashMap<>();

        for (int column = 0; column < fields.size(); column++) {
            int finalColumn = column;
            Optional<ExportTable.ExportColumn> any = fields.stream().filter(x -> x.getColumnNum() == finalColumn).findAny();
            if (any.isPresent()) {
                columnStyle.put(column, any.get().getFont());
            }
        }

        columnStyles.add(columnStyle);

        rowStyle.put(row, columnStyles);
        map.put("head", rowStyle);
    }
}


