package com.java.tutorial.project;

import com.google.common.collect.Lists;
import com.java.tutorial.tool.domain.Order;
import com.java.tutorial.tool.domain.Product;
import com.java.tutorial.tool.domain.Shop;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductTest {

    @Test
    public void test_demo_02() {

        Product aaaaProduct = Product.builder().stock(1111).skuName("aaaa").price(new BigDecimal("2.90")).build();
        Product bbbbProduct = Product.builder().stock(2222).skuName("bbbb").price(new BigDecimal("3.90")).build();
        Product ccccProduct = Product.builder().stock(3333).skuName("cccc").price(new BigDecimal("4.90")).build();
        Product ddddProduct = Product.builder().stock(4444).skuName("dddd").price(new BigDecimal("5.90")).build();

        ArrayList<Product> products = Lists.newArrayList(aaaaProduct, bbbbProduct, ccccProduct, ddddProduct);

        ArrayList<Product> productsd = Lists.newArrayList(aaaaProduct, ccccProduct, ddddProduct);

        Shop builda = Shop.builder().shopId(1000001L).shopName("builda").products(products).build();

        Shop buildb = Shop.builder().shopId(1000002L).shopName("buildb").products(productsd).build();

        Shop buildc = Shop.builder().shopId(1000003L).shopName("buildc").products(productsd).build();

        Order c = Order.builder().orderId(20001L).shops(Lists.newArrayList(builda, buildb)).build();

        Order d = Order.builder().orderId(20002L).shops(Lists.newArrayList(builda, buildb, buildc)).build();

        System.out.println(c);
        System.out.println(d);
    }

    @Test
    public void test_demo_03() {

        Product aaaaProduct = Product.builder().stock(1111).skuName("aaaa").price(new BigDecimal("2.90")).build();
        Product bbbbProduct = Product.builder().stock(2222).skuName("bbbb").price(new BigDecimal("3.90")).build();
        Product ccccProduct = Product.builder().stock(3333).skuName("cccc").price(new BigDecimal("4.90")).build();
        Product ddddProduct = Product.builder().stock(4444).skuName("dddd").price(new BigDecimal("5.90")).build();

        ArrayList<Product> products = Lists.newArrayList(aaaaProduct, bbbbProduct, ccccProduct, ddddProduct);

        ArrayList<Product> productsd = Lists.newArrayList(aaaaProduct, ccccProduct, ddddProduct);

        Shop builda = Shop.builder().shopId(1000001L).shopName("builda").products(products).build();

        Shop buildb = Shop.builder().shopId(1000002L).shopName("buildb").products(productsd).build();

        Shop buildc = Shop.builder().shopId(1000003L).shopName("buildc").products(productsd).build();

        ArrayList<Shop> shops = Lists.newArrayList(builda, buildb, buildc);

        Order c = Order.builder().orderId(20001L).shops(SerializationUtils.clone(shops)).build();

        Order d = Order.builder().orderId(20002L).shops(SerializationUtils.clone(shops)).build();

        System.out.println(c);
        System.out.println(d);
    }
}
