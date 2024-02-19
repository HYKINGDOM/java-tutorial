package com.java.tutorial.tool.producer;

import cn.hutool.core.util.RandomUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderProducerTest {

    @Autowired
    private OrderProducer orderProducer;

    @Test
    public void when_send_order_id_should_listener_order_id_then_success() {
        String randomString = RandomUtil.randomString(10);
        String sendMessage = orderProducer.sendMessage(randomString);
        Assertions.assertThat(sendMessage).isEqualTo("message send");
    }

    @Test
    public void when_send_order_id_should_listener_order_id_then_listener_throw_exception() {
        Assertions.assertThatThrownBy(() -> orderProducer.sendMessage("test")).isInstanceOf(RuntimeException.class);
    }

}