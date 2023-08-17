package com.java.demo.listener;


import com.java.demo.dto.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

/**
 * ItemWriteListener的功能和ItemReadListener、ItemReadListener类似，但是需要注意的是它接收和处理的数据对象是一个List。List的长度与chunk配置相关。
 *
 * @author hy
 */
@Slf4j
public class CustomItemWriteListener implements ItemWriteListener<Address> {
    @Override
    public void beforeWrite(List<? extends Address> items) {

    }

    @Override
    public void afterWrite(List<? extends Address> items) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends Address> items) {

    }
}
