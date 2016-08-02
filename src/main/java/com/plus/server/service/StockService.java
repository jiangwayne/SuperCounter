package com.plus.server.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plus.server.common.PageDefault;
import com.plus.server.dal.StockDAO;
import com.plus.server.model.Stock;

@Service
public class StockService {
	private static final Logger log = LoggerFactory.getLogger(StockService.class);

	@Autowired
	private StockDAO stockDAO;

	public PageInfo<Stock> selectByModel(Stock stock, Integer page, Integer pageSize) {
		log.info("查询库存，stock=" + JSON.toJSONString(stock));
		if(page == null || page <= 0){
			page = PageDefault.PAGE_NUM_DEFAULT;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = PageDefault.PAGE_SIZE_DEFAULT;
		}
		if(stock.getValid() == null){
			stock.setValid(1);
		}
		PageHelper.startPage(page, pageSize);
		List<Stock> orderList = this.stockDAO.selectByModelLike(stock);
		PageInfo<Stock> pageInfo = new PageInfo<Stock>(orderList);
		return pageInfo;
	}
	public List<Stock> selectByModel(Stock stock) {
		log.info("查询库存，stock=" + JSON.toJSONString(stock));
		if(stock.getValid() == null){
			stock.setValid(1);
		}
		List<Stock> orderList = this.stockDAO.selectByModelLike(stock);
		return orderList;
	}
	
	public Stock selectById(Long id) throws Exception{
		log.info("查询库存，id=" + id);
		if(id == null){
			throw new Exception("参数为null");
		}
		return this.stockDAO.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void saveOrUpdate(Stock stock) {
		log.info("新增或更新库存，stock=" + JSON.toJSONString(stock));
		Date curDate= new Date();
		if(stock.getId() == null){
			stock.setValid(1);
			stock.setGmtCreate(curDate);
			this.stockDAO.insert(stock);
		}else{
			stock.setGmtModify(curDate);
			this.stockDAO.updateByPrimaryKeySelective(stock);
		}
	}
}
