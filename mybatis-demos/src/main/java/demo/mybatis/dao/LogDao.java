package demo.mybatis.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import demo.mybatis.entity.Log;

@Repository
public class LogDao extends BaseDao{
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSession sqlSession;
	
	@Override
	protected SqlSession getSqlSession() {
		return sqlSession;
	}
	
	@PostConstruct
	private void init() {
		this.alias = "demo.mybatis.mapper.LogMapper";
	}
	
	public int addLog(Log log){
		return this.insert(log); 
	}
	
	public List<Log> getAll() {
		List<Log> returnList = null;
		try {
			returnList = (List<Log>)this.select("getAll");
		} catch (Exception e) {
			
		}
		return returnList;
	}
}
