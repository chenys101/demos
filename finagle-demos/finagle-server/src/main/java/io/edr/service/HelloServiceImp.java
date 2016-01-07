package io.edr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.util.ExecutorServiceFuturePool;
import com.twitter.util.Function0;
import com.twitter.util.Future;

import io.edr.finagle.api.util.FinagleRPC;
import io.edr.finagle.demo.inout.HelloRequest;
import io.edr.finagle.demo.inout.HelloResponse;
/**
 * 
 * @Description: HelloServiceImp
 * @author: chenys101
 * @date: 2016年1月7日 下午11:29:33
 */
@FinagleRPC
@Service("helloService")
public class HelloServiceImp implements io.edr.finagle.demo.service.HelloService.ServiceIface {
	
	private Logger logger = LoggerFactory.getLogger(HelloServiceImp.class);
	
	@Autowired
	private ExecutorServiceFuturePool futurePool;
	
	@Override
	public Future<HelloResponse> hello(HelloRequest req) {
		return futurePool.apply(new Function0<HelloResponse>() {
			@Override
			public HelloResponse apply() {
				HelloResponse response = new HelloResponse();
				String seq = req.getBaseReq().getSeqNo();
				if (seq.matches(".*[1|3|5|7|9]$")) {
					response.setValue("hello from client which seq ends with odd number...");
				} else {
					response.setValue("hello from client which seq ends with double number...");
				}
				logger.info("receive req:{}", req);
				return response;
			}
		});
	}
}
