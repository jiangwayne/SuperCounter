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
import com.plus.server.dal.DisplayHandbookDAO;
import com.plus.server.model.DisplayHandbook;

@Service
public class DisplayHandbookService {
	private static final Logger log = LoggerFactory.getLogger(DisplayHandbookService.class);

	@Autowired
	private DisplayHandbookDAO objectDisplayHandbookDAO;

	public PageInfo<DisplayHandbook> selectByModel(DisplayHandbook objectDisplayHandbook, Integer page, Integer pageSize) {
		log.info("查询陈列手册，objectDisplayHandbook=" + JSON.toJSONString(objectDisplayHandbook));
		if(page == null || page <= 0){
			page = PageDefault.PAGE_NUM_DEFAULT;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = PageDefault.PAGE_SIZE_DEFAULT;
		}
		if(objectDisplayHandbook.getValid() == null){
			objectDisplayHandbook.setValid(1);
		}
		PageHelper.startPage(page, pageSize);
		List<DisplayHandbook> orderList = this.objectDisplayHandbookDAO.selectByModelLike(objectDisplayHandbook);
		PageInfo<DisplayHandbook> pageInfo = new PageInfo<DisplayHandbook>(orderList);
		return pageInfo;
	}
	public List<DisplayHandbook> selectByModel(DisplayHandbook objectDisplayHandbook) {
		log.info("查询陈列手册，objectDisplayHandbook=" + JSON.toJSONString(objectDisplayHandbook));
		if(objectDisplayHandbook.getValid() == null){
			objectDisplayHandbook.setValid(1);
		}
		List<DisplayHandbook> orderList = this.objectDisplayHandbookDAO.selectByModelLike(objectDisplayHandbook);
		return orderList;
	}
	
	public DisplayHandbook selectById(Long id) throws Exception{
		log.info("查询陈列手册，id=" + id);
		if(id == null){
			throw new Exception("参数为null");
		}
		return this.objectDisplayHandbookDAO.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void saveOrUpdate(DisplayHandbook objectDisplayHandbook) {
		log.info("新增或更新陈列手册，objectDisplayHandbook=" + JSON.toJSONString(objectDisplayHandbook));
		Date curDate= new Date();
		if(objectDisplayHandbook.getParentId() == null){
			objectDisplayHandbook.setParentId(0L);//最上层，parentid设置为0
		}
		if(objectDisplayHandbook.getId() == null){
			objectDisplayHandbook.setValid(1);
			objectDisplayHandbook.setGmtCreate(curDate);
			this.objectDisplayHandbookDAO.insert(objectDisplayHandbook);
		}else{
			objectDisplayHandbook.setGmtModify(curDate);
			this.objectDisplayHandbookDAO.updateByPrimaryKeySelective(objectDisplayHandbook);
		}
	}
}
