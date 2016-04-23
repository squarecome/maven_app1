package com.fanglai.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanglai.dao.BaseDao;
import com.fanglai.dao.OrderDao;
import com.fanglai.model.Order;
import com.fanglai.model.Test;

@Transactional
@Service
public class OrderService {
	
	@Resource
	private OrderDao orderDao;
	private Log log = LogFactory.getLog("OrderService");
	
	@Transactional(readOnly=true)
	public Order get(String id){
		List<Order> orders = orderDao.getSession().createQuery("from Order").list();
		if(orders == null || orders.size() == 0){
			return null;
		}else{
			return orders.get(0);
		}
	}

}
