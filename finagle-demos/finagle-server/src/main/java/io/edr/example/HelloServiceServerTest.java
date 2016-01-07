package io.edr.example;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.tracing.Trace;
import com.twitter.util.Await;
import com.twitter.util.ExecutorServiceFuturePool;
import com.twitter.util.Function0;
import com.twitter.util.Future;

import io.edr.finagle.demo.inout.HelloRequest;
import io.edr.finagle.demo.inout.HelloResponse;
import io.edr.finagle.demo.inout.common.BaseResponse;

/**
 * 
 * @Description: HelloServiceServerTest start without zookeeper
 * @author: chenys101
 * @date: 2016年1月7日 下午11:29:55
 */
public class HelloServiceServerTest implements io.edr.finagle.demo.service.HelloService.ServiceIface {
	
	private static Logger logger = LoggerFactory.getLogger(HelloServiceServerTest.class);
	private ExecutorServiceFuturePool futurePool = new ExecutorServiceFuturePool(Executors.newFixedThreadPool(1));
	
	@Override
	public Future<HelloResponse> hello(HelloRequest req) {
		return futurePool.apply(new Function0<HelloResponse>() {
			@Override
			public HelloResponse apply() {
				BaseResponse baseResp = new BaseResponse();
				baseResp.setSeqNo(req.getBaseReq().getSeqNo());
				HelloResponse response = new HelloResponse();
				response.setBaseResp(baseResp);
				response.setValue("Server have got it...");
				logger.info("receive req:{}", req);
				return response;
			}
		});
	}
	
	/**
	 * Start the hello service server.
	 */
	public static void main(final String[] args) throws Throwable {
		final String bindAddr = "localhost:5555";
		logger.info("HelloServiceTest listening on: " + bindAddr);
		final ListeningServer server = Thrift.serveIface(bindAddr, new HelloServiceServerTest());
		Trace.enable();
		Await.ready(server);
	}
}
