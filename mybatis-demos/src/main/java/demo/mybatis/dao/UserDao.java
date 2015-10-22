package demo.mybatis.dao;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import demo.mybatis.entity.User;

@Repository
public class UserDao extends BaseDao{
	
	@PostConstruct
	private void init() {
		this.alias = "demo.mybatis.mapper.UserMapper";
	}
	
	public int addUser(User user){
		return this.insert(user); 
	}
}
