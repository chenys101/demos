package io.edr.start;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @Description: start up service
 * @author: chenys101
 * @date: 2016年1月7日 下午11:28:37
 */
public class Main {

	public static void main(String[] args) {
		try {
			AbstractApplicationContext context = new ClassPathXmlApplicationContext("/spring-conf/spring.xml");
			context.registerShutdownHook();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
