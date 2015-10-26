package test.mybatis.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.mybatis.entity.Log;
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
	
	@Test
	public void getAll() {
		List<Log>  list = logService.getAll();
		System.out.println(list);
	}
	
}
