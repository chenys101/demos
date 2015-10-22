package demo.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.mybatis.dao.UserDao;
import demo.mybatis.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public int addUser(String name, int age){
		User user = new User();
		user.setName(name);
		user.setAge(age);
		return userDao.addUser(user);
	}
}
