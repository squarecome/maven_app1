package com.fanglai.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanglai.dao.BaseDao;
import com.fanglai.dao.TestDao;
import com.fanglai.model.Test;
import com.fanglai.utils.page.Page;

@Transactional
@Service
public class TestService<T> {
	@Resource
	private TestDao testDao;
	private Log log = LogFactory.getLog("TestService");
	
	public void save(Test transientInstance) {
		log.info("saving Test instance");
		try {
			testDao.getSession().save(transientInstance);
			log.info("save successful");
			update(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Test transientInstance) {
		log.info("saving Test instance");
		try {
			transientInstance.setBirthday(new Date());
			testDao.getSession().saveOrUpdate(transientInstance);
			log.info("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	@Transactional(readOnly=true)
	public List<Test> list(){
		return testDao.getSession().createQuery("from Test").list();
	}
	
	@Transactional(readOnly=true)
	public Page<Test> page(Page<Test> page){
		return testDao.findPage("from Test", page);
	}
}
