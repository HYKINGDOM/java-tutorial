package com.java.tutorial.project.excel.convert;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.java.tutorial.project.excel.annotation.ExcelDictFormat;
import com.java.tutorial.project.excel.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * 字典格式化转换处理
 *
 * @author meta
 */
@Slf4j
public class ExcelDictConvert implements Converter<Object> {

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
        ExcelDictFormat anno = getAnnotation(contentProperty.getField());
        String type = anno.dictType();
        String label = cellData.getStringValue();
        String value = null;
        if (StrUtil.isBlank(type)) {
            value = ExcelUtil.reverseByExp(label, anno.readConverterExp(), anno.separator());
        } else {
            //value = SpringUtil.getBean(DictService.class).getDictValue(type, label, anno.separator());
        }
        return Convert.convert(contentProperty.getField().getType(), value);
    }

    @Override
    public WriteCellData<String> convertToExcelData(Object object, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (ObjectUtil.isNull(object)) {
            return new WriteCellData<>("");
        }
        ExcelDictFormat anno = getAnnotation(contentProperty.getField());
        String type = anno.dictType();
        String value = Convert.toStr(object);
        String label = null;
        if (StrUtil.isBlank(type)) {
            label = ExcelUtil.convertByExp(value, anno.readConverterExp(), anno.separator());
        } else {
            //label = SpringUtil.getBean(DictService.class).getDictLabel(type, value, anno.separator());
        }
        return new WriteCellData<>(label);
    }

    private ExcelDictFormat getAnnotation(Field field) {
        return AnnotationUtil.getAnnotation(field, ExcelDictFormat.class);
    }
}
