package demo.mybatis.dao;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import demo.mybatis.entity.Log;

@Repository
public class LogDao extends BaseDao{
	
	@PostConstruct
	private void init() {
		this.alias = "demo.mybatis.mapper.LogMapper";
	}
	
	public int addLog(Log log){
		return this.insert(log); 
	}
}
