package test.mybatis.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.mybatis.service.LogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring.xml" })
public class LogTest {
	@Autowired
	private LogService logService;
	
	@Test
	public void addLog(){
		logService.addLog("≤‚ ‘»’÷æ", 1);
	}
}
