package com.java.tutorial.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;

public class SpringBootProjectApplicationTests {

	@Test
	public void test_demo() {

		System.out.println(HttpMethod.POST.name());
	}

}
