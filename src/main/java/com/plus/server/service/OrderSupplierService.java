package com.plus.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plus.server.common.PageDefault;
import com.plus.server.dal.OrderSupplierDAO;
import com.plus.server.model.OrderSupplier;

@Service
public class OrderSupplierService {
	private static final Logger log = LoggerFactory.getLogger(OrderSupplierService.class);

	@Autowired
	private OrderSupplierDAO orderSupplierDAO;

	public PageInfo<OrderSupplier> selectByModel(OrderSupplier orderSupplier, Integer page, Integer pageSize) {
		log.info("查询生产加工单，orderSupplier=" + JSON.toJSONString(orderSupplier));
		if(page == null || page <= 0){
			page = PageDefault.PAGE_NUM_DEFAULT;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = PageDefault.PAGE_SIZE_DEFAULT;
		}
		if(orderSupplier.getValid() == null){
			orderSupplier.setValid(1);
		}
		PageHelper.startPage(page, pageSize);
		List<OrderSupplier> orderList = this.orderSupplierDAO.selectByModelLike(orderSupplier);
		PageInfo<OrderSupplier> pageInfo = new PageInfo<OrderSupplier>(orderList);
		return pageInfo;
	}
	public List<OrderSupplier> selectByModel(OrderSupplier orderSupplier) {
		log.info("查询生产加工单，orderSupplier=" + JSON.toJSONString(orderSupplier));
		if(orderSupplier.getValid() == null){
			orderSupplier.setValid(1);
		}
		List<OrderSupplier> orderList = this.orderSupplierDAO.selectByModelLike(orderSupplier);
		return orderList;
	}
	
	public OrderSupplier selectById(Long id) throws Exception{
		log.info("查询生产加工单，id=" + id);
		if(id == null){
			throw new Exception("参数为null");
		}
		return this.orderSupplierDAO.selectByPrimaryKey(id);
	}
	
}
