package demo.mybatis.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDao {
	protected String alias = "";
	
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	public int insert(Object object){
		return sqlSessionFactory.openSession().insert(alias + ".insert", object);
	}
}
