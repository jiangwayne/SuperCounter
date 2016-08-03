package com.plus.server.service;

import java.text.SimpleDateFormat;
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
import com.plus.server.dal.DailyOrderDAO;
import com.plus.server.model.DailyOrder;

@Service
public class DailyOrderService {
	private static final Logger log = LoggerFactory.getLogger(DailyOrderService.class);

	@Autowired
	private DailyOrderDAO dailyOrderDAO;

	public PageInfo<DailyOrder> selectByModel(DailyOrder dailyOrder, Integer page, Integer pageSize) {
		log.info("查询日常补货，dailyOrder=" + JSON.toJSONString(dailyOrder));
		if(page == null || page <= 0){
			page = PageDefault.PAGE_NUM_DEFAULT;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = PageDefault.PAGE_SIZE_DEFAULT;
		}
		if(dailyOrder.getValid() == null){
			dailyOrder.setValid(1);
		}
		PageHelper.startPage(page, pageSize);
		List<DailyOrder> orderList = this.dailyOrderDAO.selectByModelLike(dailyOrder);
		PageInfo<DailyOrder> pageInfo = new PageInfo<DailyOrder>(orderList);
		return pageInfo;
	}
	public List<DailyOrder> selectByModel(DailyOrder dailyOrder) {
		log.info("查询日常补货，dailyOrder=" + JSON.toJSONString(dailyOrder));
		if(dailyOrder.getValid() == null){
			dailyOrder.setValid(1);
		}
		List<DailyOrder> orderList = this.dailyOrderDAO.selectByModelLike(dailyOrder);
		return orderList;
	}
	
	public DailyOrder selectById(Long id) throws Exception{
		log.info("查询日常补货，id=" + id);
		if(id == null){
			throw new Exception("参数为null");
		}
		return this.dailyOrderDAO.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void saveOrUpdate(DailyOrder dailyOrder) {
		log.info("新增或更新日常补货，dailyOrder=" + JSON.toJSONString(dailyOrder));
		Date curDate= new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		if(dailyOrder.getId() == null){
			dailyOrder.setValid(1);
			dailyOrder.setGmtCreate(curDate);
			this.dailyOrderDAO.insert(dailyOrder);
			//生成编码
			DailyOrder param = new DailyOrder();
			param.setId(dailyOrder.getId());
			param.setDailyOrderNo("DO"+f.format(curDate)+(dailyOrder.getId() % 10000 + 10000));
			dailyOrder.setGmtModify(curDate);
			this.dailyOrderDAO.updateByPrimaryKeySelective(param);
		}else{
			dailyOrder.setDailyOrderNo(null);//不更新编码
			dailyOrder.setGmtModify(curDate);
			this.dailyOrderDAO.updateByPrimaryKeySelective(dailyOrder);
		}
	}
}
