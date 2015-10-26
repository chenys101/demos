package demo.mybatis.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import demo.mybatis.entity.User;
import demo.mybatis.mapper.UserMapper;

@Repository
public class UserDao extends BaseDao{
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSession sqlSession;
	
	@Override
	protected SqlSession getSqlSession() {
		return sqlSession;
	}
	
	@PostConstruct
	private void init() {
		this.alias = "demo.mybatis.mapper.UserMapper";
	}
	@Autowired
	private UserMapper userMapper;
	
	public int addUser(User user){
		return this.insert(user); 
	}
	
	public List<User> getByName(String name){
		List<User> userList = userMapper.getByName(name);
		return userList;
	}
}
