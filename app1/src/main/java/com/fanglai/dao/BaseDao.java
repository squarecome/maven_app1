package com.fanglai.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.fanglai.utils.GenericsUtils;
import com.fanglai.utils.page.Page;
import com.fanglai.utils.page.PageUtil;

@Component
public class BaseDao<T> {
	
	@Resource
	SessionFactory sessionFactory;
	
	protected Log log = LogFactory.getLog(getClass());
	
	protected Class<T> entityClass;
	
	public BaseDao() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
	
	public Page<T> findPage(String hql,Page<T> page){
		final String totalHql = matchHql(removeOrders(hql));
		Query query = getSession().createQuery(hql); 
	    query.setFirstResult(page.getBeginIndex());
	    query.setMaxResults(page.getPageSize());
	    int totalCount = getCountByHql(totalHql);
	    Page<T> result = PageUtil.createPage(page, totalCount);
	    result.setData(query.list());
	    return result;
	}
	
	/**
	   * 通过hql查记录数
	   * @param hql
	   * @return
	   */
	  public int getCountByHql(final String hql) {
		  Query query = getSession().createQuery(hql);
		  int count = ((Number) query.iterate().next()).intValue();
		  return count;       
	  }
	  
	  public String matchHql(String hql) {  
		  String hal = "select count(*) " + hql.substring(hql.indexOf("from"));   
	      return hal;  
	  }
	  
	  public String removeOrders(String hql) {  
	      Matcher matcher = pattern.matcher(hql);  
	      StringBuffer buf = new StringBuffer();  
	      while (matcher.find()) {  
	          matcher.appendReplacement(buf, "");  
	      }  
	      matcher.appendTail(buf);  
	      return buf.toString();  
	  }
	  
	  private static Pattern pattern;
	  
	  static{
		  pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);   
	  }
}
