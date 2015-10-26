package demo.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDao {
	protected String alias = "";
	
	protected abstract SqlSession getSqlSession();
	
	public int insert(Object object){
		return this.getSqlSession().insert(alias + ".insert", object);
	}
	
	protected Object select(String statementName, Object params) throws Exception{
		return this.getSqlSession().selectList(alias + "." + statementName, params);
	}
	
	protected Object select(String statementName) throws Exception{
		return this.getSqlSession().selectList(alias + "." + statementName);
	}
	
	protected Object selectOne(String statementName, Object params) throws Exception{
		return this.getSqlSession().selectOne(alias + "." + statementName, params);
	}
	
	protected Object selectOne(String statementName) throws Exception{
		return this.getSqlSession().selectOne(alias + "." + statementName);
	}
}
