package com.java.kscs.listener;

import com.java.kscs.dto.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;

/**
 * ItemProcessListener和ItemReadListener类似，是围绕着ItemProcessor进行处理的
 *
 * @author hy
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
