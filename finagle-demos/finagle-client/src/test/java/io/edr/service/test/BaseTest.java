package io.edr.service.test;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 * @Description: 测试用例基类 
 * @author cheys101
 * @date 2016年1月7日 下午11:01:04 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-conf/spring-client.xml" })
@Ignore
public class BaseTest extends AbstractJUnit4SpringContextTests{

}
  