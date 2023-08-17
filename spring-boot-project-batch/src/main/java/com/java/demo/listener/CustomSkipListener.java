package com.java.demo.listener;

import com.java.demo.dto.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;

/**
 * ItemReadListener、ItemProcessListener和ItemWriteListener都提供了错误拦截处理的机制，但是没有处理跳过（skip）的数据记录。
 * 因此框架提供了SkipListener来专门处理那么被跳过的记录：
 * <p>
 * 跳过的元素只会出现一次。 SkipListener始终在事物提交之前被调用，这样可以保证监听器使用的事物资源不会被业务事物影响。
 *
 * @author hy
 */
@Slf4j
public class CustomSkipListener implements SkipListener<Address, Address> {
    @Override
    public void onSkipInRead(Throwable t) {
        // Read期间导致跳过的异常
    }

    @Override
    public void onSkipInWrite(Address item, Throwable t) {
        // Write期间导致跳过的异常
    }

    @Override
    public void onSkipInProcess(Address item, Throwable t) {
        // Process期间导致跳过的异常
    }
}
