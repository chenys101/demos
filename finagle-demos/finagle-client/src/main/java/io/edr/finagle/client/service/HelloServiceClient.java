package io.edr.finagle.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.util.Await;

import io.edr.finagle.demo.inout.HelloRequest;
import io.edr.finagle.demo.inout.HelloResponse;
import io.edr.finagle.demo.inout.common.BaseRequest;
import io.edr.finagle.demo.service.HelloService;
import io.edr.finagle.util.DemoServiceFactory;

/**
 * 
 * @Description: HelloServiceClient
 * @author: chenys101
 * @date: 2016年1月7日 下午11:04:43
 */
@Service
public class HelloServiceClient {

	private static Logger logger = LoggerFactory.getLogger(HelloServiceClient.class);
	
	@Autowired
	private DemoServiceFactory demoServiceFactory;
	
	public void hello() {
		try {
			// 通过factory获得的service客户端对象是线程安全的。
			HelloService.ServiceIface client = demoServiceFactory.getHelloService();
			for (int i = 0; i < 10; i++) {
				BaseRequest baseReq = new BaseRequest();
				baseReq.setSeqNo("1000101"+i);
				HelloRequest req = new HelloRequest();
				req.setBaseReq(baseReq);
				req.setName("client: " + i);
				HelloResponse response = Await.result(client.hello(req));
				logger.info("Hello server:{}", response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
