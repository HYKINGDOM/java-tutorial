package com.java.tutorial.project.excel.convert;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.java.tutorial.project.excel.annotation.ExcelEnumFormat;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.excel.enums.CellDataTypeEnum.BOOLEAN;
import static com.alibaba.excel.enums.CellDataTypeEnum.DIRECT_STRING;
import static com.alibaba.excel.enums.CellDataTypeEnum.NUMBER;
import static com.alibaba.excel.enums.CellDataTypeEnum.RICH_TEXT_STRING;
import static com.alibaba.excel.enums.CellDataTypeEnum.STRING;

/**
 * 枚举格式化转换处理
 *
 * @author Liang
 */
@Slf4j
public class ExcelEnumConvert implements Converter<Object> {

    @Override
    public Class<Object> supportJavaTypeKey() {
        return Object.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public Object convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        cellData.checkEmpty();
        // Excel中填入的是枚举中指定的描述
        Object textValue = null;
        if (cellData.getType() == STRING || cellData.getType() == DIRECT_STRING || cellData.getType() == RICH_TEXT_STRING) {
            textValue = cellData.getStringValue();
        } else if (cellData.getType() == NUMBER) {
            textValue = cellData.getNumberValue();
        } else if (cellData.getType() == BOOLEAN) {
            textValue = cellData.getBooleanValue();
        } else {
            throw new IllegalArgumentException("单元格类型异常!");
        }

        // 如果是空值
        if (ObjectUtil.isNull(textValue)) {
            return null;
        }
        Map<Object, String> enumCodeToTextMap = beforeConvert(contentProperty);
        // 从Java输出至Excel是code转text
        // 因此从Excel转Java应该将text与code对调
        Map<Object, Object> enumTextToCodeMap = new HashMap<>();
        enumCodeToTextMap.forEach((key, value) -> enumTextToCodeMap.put(value, key));
        // 应该从text -> code中查找
        Object codeValue = enumTextToCodeMap.get(textValue);
        return Convert.convert(contentProperty.getField().getType(), codeValue);
    }

    @Override
    public WriteCellData<String> convertToExcelData(Object object, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (ObjectUtil.isNull(object)) {
            return new WriteCellData<>("");
        }
        Map<Object, String> enumValueMap = beforeConvert(contentProperty);
        String value = Convert.toStr(enumValueMap.get(object), "");
        return new WriteCellData<>(value);
    }

    private Map<Object, String> beforeConvert(ExcelContentProperty contentProperty) {
        ExcelEnumFormat anno = getAnnotation(contentProperty.getField());
        Map<Object, String> enumValueMap = new HashMap<>();
        Enum<?>[] enumConstants = anno.enumClass().getEnumConstants();
        for (Enum<?> enumConstant : enumConstants) {
            Object codeValue = ReflectUtil.invoke(enumConstant, anno.codeField());
            String textValue = ReflectUtil.invoke(enumConstant, anno.textField());
            enumValueMap.put(codeValue, textValue);
        }
        return enumValueMap;
    }

    private ExcelEnumFormat getAnnotation(Field field) {
        return AnnotationUtil.getAnnotation(field, ExcelEnumFormat.class);
    }
}
