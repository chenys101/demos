package io.edr.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import com.twitter.util.Future;

import io.edr.finagle.demo.inout.HelloRequest;
import io.edr.finagle.demo.inout.HelloResponse;
import io.edr.finagle.demo.inout.common.BaseRequest;
import io.edr.finagle.demo.service.HelloService;

/**
 * 
 * @Description: HelloServiceClientTest start without zookeeper
 * @author: chenys101
 * @date: 2016年1月7日 下午11:30:34
 */
public final class HelloServiceClientTest {

    // Logging
    private static Logger logger = LoggerFactory.getLogger(HelloServiceClientTest.class);

    /**
     * Call the sayHello function on the remote hello service.
     */
    public static void main(final String[] args) throws Throwable {
        final String bindAddr = "localhost:5555";
        logger.info("Calling HelloService on: " + bindAddr);
        final HelloService.ServiceIface client = Thrift.newIface(bindAddr, HelloService.ServiceIface.class);
        final BaseRequest baseReq = new BaseRequest();
        final HelloRequest req = new HelloRequest();
        baseReq.setSeqNo("123456123456");
        req.setBaseReq(baseReq);
        req.setName("HelloServiceClientTest");
        Future<HelloResponse> response = client.hello(req);
        logger.info("response:{}",Await.result(response));
    }
}