package io.edr.dubbo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.edr.dubbo.service.DemoService;

/** 
 * 类功能描述: 实现类
 *
*/
public class DemoServiceImpl implements DemoService{
	
	public Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
	
	@Override
	public String sayHello(String name) {
		logger.info("name:{}", name);
		return "Hello " + name;
	}
}
