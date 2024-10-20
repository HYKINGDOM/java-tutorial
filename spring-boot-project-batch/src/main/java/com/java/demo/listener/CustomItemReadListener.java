package com.java.demo.listener;

import com.java.demo.dto.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

/**
 * 该接口用于对Reader相关的事件进行监控
 *
 * @author meta
 */
@Slf4j
public class CustomItemReadListener implements ItemReadListener<Address> {
    @Override
    public void beforeRead() {
        // beforeRead在每次Reader调用之前被调用
    }

    @Override
    public void afterRead(Address address) {
        // afterRead在每次Reader成功返回之后被调用
    }

    @Override
    public void onReadError(Exception ex) {
        // 而onReadError会在出现异常之后被调用，可以将其用于记录异常日志。
    }
}
