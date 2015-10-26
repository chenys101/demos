package demo.mybatis.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import demo.mybatis.entity.User;

@Repository
public interface UserMapper {
	List<User> getByName(String name);
}
