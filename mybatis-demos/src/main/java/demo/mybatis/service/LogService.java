package demo.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.mybatis.dao.LogDao;
import demo.mybatis.entity.Log;

@Service
public class LogService {
	@Autowired
	private LogDao logDao;
	
	@Transactional
	public int addLog(String action, int userId){
		Log log = new Log();
		log.setAction(action);
		log.setUserId(userId);
		return logDao.addLog(log);
	}
}
