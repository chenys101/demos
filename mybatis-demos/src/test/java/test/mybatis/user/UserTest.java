package test.mybatis.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.mybatis.entity.User;
import demo.mybatis.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring.xml" })
public class UserTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void addUser(){
		userService.addUser("����aa", 1);
	}
	
	@Test
	public void getByName(){
		List<User> userList =userService.getByName("����aa");
		System.out.println(userList);
	}
}
