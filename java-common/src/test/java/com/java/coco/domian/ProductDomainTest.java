package com.java.coco.domian;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDomainTest {

    private List<TalentInfo> talentInfos = Lists.newArrayList();

    private Map<String, Product> productDTOMap = new HashMap<>();

    @BeforeEach
    public void init_data() {

        productDTOMap.put("123", Product.builder().productId(123L).productName("sdasda1").build());
        productDTOMap.put("456", Product.builder().productId(456L).productName("sdasda2").build());
        productDTOMap.put("789", Product.builder().productId(789L).productName("sdasda3").build());
        productDTOMap.put("135", Product.builder().productId(135L).productName("sdasda4").build());
        productDTOMap.put("147", Product.builder().productId(147L).productName("sdasda5").build());
        productDTOMap.put("168", Product.builder().productId(168L).productName("sdasda6").build());

    }

    @Test
    public void test01() {

        List<TalentInfo> talentInfos = Lists.newArrayList();

        TalentInfo talentInfo01 = TalentInfo.builder().id(111222L)
            .products(Lists.newArrayList(productDTOMap.get("123"), productDTOMap.get("456"), productDTOMap.get("789")))
            .build();

        talentInfos.add(talentInfo01);

        TalentInfo talentInfo02 = TalentInfo.builder().id(111333L)
            .products(Lists.newArrayList(productDTOMap.get("123"), productDTOMap.get("456"), productDTOMap.get("789")))
            .build();

        talentInfos.add(talentInfo02);

        System.out.println(JSON.toJSONString(talentInfos));

        talentInfos.forEach(talentInfo -> {
            talentInfo.getProducts().forEach(product -> {

                if (product.getProductId() == 123L) {
                    product.setProductName("test_name");
                }
            });
        });

        System.out.println(JSON.toJSONString(talentInfos));
    }

    @Test
    public void test02() {

        List<TalentInfo> talentInfos = Lists.newArrayList();

        TalentInfo talentInfo01 = TalentInfo.builder().id(111222L).products(
            Lists.newArrayList(BeanUtil.toBean(productDTOMap.get("123"), Product.class),
                BeanUtil.toBean(productDTOMap.get("456"), Product.class),
                BeanUtil.toBean(productDTOMap.get("789"), Product.class))).build();

        talentInfos.add(talentInfo01);

        TalentInfo talentInfo02 = TalentInfo.builder().id(111333L).products(
            Lists.newArrayList(BeanUtil.toBean(productDTOMap.get("123"), Product.class),
                BeanUtil.toBean(productDTOMap.get("456"), Product.class),
                BeanUtil.toBean(productDTOMap.get("789"), Product.class))).build();

        talentInfos.add(talentInfo02);

        System.out.println(JSON.toJSONString(talentInfos));

        talentInfos.forEach(talentInfo -> {
            talentInfo.getProducts().forEach(product -> {

                if (product.getProductId() == 123L) {
                    product.setProductName("test_name");
                }
            });
        });

        System.out.println(JSON.toJSONString(talentInfos));
    }

}
