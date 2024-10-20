package com.java.demo.listener;

import com.java.demo.dto.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;

/**
 * ItemProcessListener和ItemReadListener类似，processor
 *
 * @author meta
 */
@Slf4j
public class CustomItemProcessListener implements ItemProcessListener<Address, Address> {
    @Override
    public void beforeProcess(Address item) {
        // processor执行之前
    }

    @Override
    public void afterProcess(Address item, Address result) {
        // processor执行成功之后
    }

    @Override
    public void onProcessError(Address item, Exception e) {
        // processor执行出现异常
    }
}
