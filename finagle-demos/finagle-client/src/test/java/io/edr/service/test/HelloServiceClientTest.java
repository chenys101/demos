package io.edr.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.edr.finagle.client.service.HelloServiceClient;

/**
 * 
 * @Description: HelloServiceClientTest
 * @author: chenys101
 * @date: 2016年1月7日 下午11:04:24
 */
public class HelloServiceClientTest extends BaseTest{
	
	@Autowired
	private HelloServiceClient helloServiceClient;
	
	@Test
	public void testHello() {
		helloServiceClient.hello();
	}
}
