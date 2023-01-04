package com.java.kscs.process;

import cn.hutool.core.util.StrUtil;
import com.java.kscs.dto.Address;
import com.java.kscs.dto.BlogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

/**
 * 需要执行super.process(item)才会调用自定义校验器
 * 对数据进行简单的处理
 *
 * @author HY
 */
@Slf4j
public class AddressItemProcessor extends ValidatingItemProcessor<Address> {
    @Override
    public Address process(@Nonnull Address address) throws ValidationException {
        super.process(address);
        if (StrUtil.isEmpty(address.getCityId())) {
            address.setCityId("0");
        }
        log.info("Address Item Processor :{}", address);
        return address;
    }
}
