package com.java.kscs.listener;

import com.java.kscs.dto.BlogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

import static java.lang.String.format;

/**
 *
 */
@Slf4j
public class MyReadListener implements ItemReadListener<BlogInfo> {


    @Override
    public void beforeRead() {
        log.info("MyReadListener beforeRead");
    }

    @Override
    public void afterRead(BlogInfo item) {

        log.info("MyReadListener afterRead {}", item.getBlogItem());
    }

    @Override
    public void onReadError(Exception ex) {
        try {
            log.info(format("MyReadListener onReadError %s%n", ex.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
