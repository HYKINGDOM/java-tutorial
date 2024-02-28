package com.java.tutorial.project.excel.handle;

import com.alibaba.excel.util.BooleanUtils;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * 网址设置为超链接
 * @author meta
 */
@Slf4j
public class HyperLinkStyleHandler implements CellWriteHandler {

    public static final String PROTOCOL = "https://";

    /**
     * 在写入excel之前，对cell进行处理 包含https://的内容设置为超链接
     *
     * @param context
     */
    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        Cell cell = context.getCell();
        String stringCellValue = cell.toString();
        if (BooleanUtils.isFalse(context.getHead()) && stringCellValue.startsWith(PROTOCOL)) {
            CreationHelper createHelper = context.getWriteSheetHolder().getSheet().getWorkbook().getCreationHelper();

            CellStyle hlinkStyle = context.getWriteSheetHolder().getSheet().getWorkbook().createCellStyle();
            Font hlinkFont = context.getWriteSheetHolder().getSheet().getWorkbook().createFont();
            // 设置字体颜色为蓝色
            hlinkFont.setColor(IndexedColors.BLUE.getIndex());
            // 将字体样式应用到单元格样式
            hlinkStyle.setFont(hlinkFont);

            Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.URL);
            hyperlink.setAddress(stringCellValue);
            cell.setHyperlink(hyperlink);

            // 设置单元格样式为包含蓝色超链接字体的样式
            cell.setCellStyle(hlinkStyle);
        }
    }
}
