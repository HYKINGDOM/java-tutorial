package com.java.util.javautil.system.javatest;

/**
 * 文件流下载
 *
 */
public class FileDownload {




//    @Override
//    @OperationLogDetail(detail = "根据参数,导出Excel数据",operationUnit = OperationUnit.prjmanage, operationType = OperationType.EXPORT)
//    @DataSourceTransaction(name = "second")
//    public ResponseEntity<byte[]> exportBUDataExcelByDate(Map<String, Object> param) {
//        XSSFWorkbook book = new XSSFWorkbook();
//        this.excelColumn = 16;
//        List<Map<String, Object>> listMap = (List<Map<String, Object>>) param.get(excelSheetSize[0]);
//        createExcelFileBUSelfMeasuring(book, excelSheetName[0], listMap);
//
//        for (int i = 1; i < 3; i++) {
//            List<Map<String, Object>> listMapMonthToMonth = (List<Map<String, Object>>) param.get(excelSheetSize[i]);
//            createExcelFileBUMonthToMonth(book, excelSheetName[i], listMapMonthToMonth);
//        }
//
//        String kpiProperty = "KPI统计分析" + format(getNowDate(), YYYYMMDDHHMMSS) + ".xlsx";
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        HttpHeaders headers = null;
//        try {
//            book.write(outputStream);
//            outputStream.close();
//            headers = new HttpHeaders();
//            //通知浏览器以attachment（下载方式）打开图片
//            headers.setContentDispositionFormData("attachment;filename*=UTF-8", kpiProperty);
//            //application/octet-stream ： 二进制流数据（最常见的文件下载）。
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//
//        } catch (IOException e) {
//            e.getMessage();
//        }
//        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.CREATED);
//    }
}
