package com.java.tutorial.project.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import com.java.tutorial.project.domain.OrderItem;
import com.java.tutorial.project.domain.Page;
import com.java.tutorial.project.domain.PageRequest;
import com.java.tutorial.project.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kscs
 */
@Slf4j
@Service
public class OrderItemServiceImpl implements OrderItemService {


    private final Snowflake snowflake = new Snowflake(3);

    @Override
    public Page<OrderItem> findPage(PageRequest pageRequest) {

        int pageNumber = pageRequest.getPageNumber();
        int pageSize = pageRequest.getPageSize();


        List<OrderItem> data = new ArrayList<>(pageSize);
        for (int i = 0; i < pageSize; i++) {
            data.add(new OrderItem(snowflake.nextId(), RandomUtil.randomString(10)));

        }



        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 假设总共有 10000 条数据
        boolean hasNext = (pageNumber + 1) * pageSize < 10000;

        log.info("pageNumber:{},pageSize:{},hasNext:{}", pageNumber, pageSize, hasNext);
        return new Page<>(data, hasNext);
    }
}
