package com.java.tutorial.project;

import com.alibaba.fastjson2.JSON;
import com.java.tutorial.project.domain.OrderContext;
import com.java.tutorial.project.domain.OrderParam;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SpringBootProjectApplicationTests {

	@Resource
	private FlowExecutor flowExecutor;

	@Test
	public void runOrderFlowTest(){
		OrderParam orderParam = OrderParam.builder().orderType(1).userId("coderacademy").userName("码农Academy").build();
		LiteflowResponse response = flowExecutor.execute2Resp("order_handle", orderParam, OrderContext.class);
		OrderContext orderContext = response.getContextBean(OrderContext.class);
		System.out.println(JSON.toJSONString(orderContext));
	}


	@Test
	public void runOrderFlowTest2(){
		OrderParam orderParam = OrderParam.builder().orderType(2).userId("coder-academy").userName("码农-Academy").build();
		LiteflowResponse response = flowExecutor.execute2Resp("order_handle", orderParam, OrderContext.class);
		OrderContext orderContext = response.getContextBean(OrderContext.class);
		System.out.println(JSON.toJSONString(orderContext));
	}

}
