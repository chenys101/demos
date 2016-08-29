package io.edr.dubbo.consumer;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.edr.dubbo.service.DemoService;

public class DemoConsumer {
	public static void main(String[] args) {
		AbstractApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("/spring-conf/spring.xml");
			context.registerShutdownHook();
			DemoService demoService = (DemoService) context.getBean("demoService");
            String hello = demoService.sayHello("哈哈哈哈"); // 执行远程方法
            System.out.println(hello); // 显示调用结果
            context.stop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (context != null) {
                context.close();
            }
        }
	}
}
