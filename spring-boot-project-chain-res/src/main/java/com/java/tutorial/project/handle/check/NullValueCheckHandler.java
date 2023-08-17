package com.java.tutorial.project.handle.check;

import com.java.tutorial.project.config.AbstractCheckHandler;
import com.java.tutorial.project.domian.ProductVO;
import com.java.tutorial.project.util.ErrorCode;
import com.java.tutorial.project.util.Result;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 空值校验处理器
 * @author hy
 */
@Component
public class NullValueCheckHandler extends AbstractCheckHandler {

    @Override
    public Result handle(ProductVO param) {
        System.out.println("空值校验 Handler 开始...");

        //降级：如果配置了降级，则跳过此处理器，执行下一个处理器
        if (super.getConfig().getDown()) {
            System.out.println("空值校验 Handler 已降级，跳过空值校验 Handler...");
            return super.next(param);
        }

        //参数必填校验
        if (Objects.isNull(param)) {
            return Result.failure(ErrorCode.PARAM_NULL_ERROR);
        }
        //SkuId商品主键参数必填校验
        if (Objects.isNull(param.getSkuId())) {
            return Result.failure(ErrorCode.PARAM_SKU_NULL_ERROR);
        }
        //Price价格参数必填校验
        if (Objects.isNull(param.getPrice())) {
            return Result.failure(ErrorCode.PARAM_PRICE_NULL_ERROR);
        }
        //Stock库存参数必填校验
        if (Objects.isNull(param.getStock())) {
            return Result.failure(ErrorCode.PARAM_STOCK_NULL_ERROR);
        }

        System.out.println("空值校验 Handler 通过...");

        //执行下一个处理器
        return super.next(param);
    }
}

