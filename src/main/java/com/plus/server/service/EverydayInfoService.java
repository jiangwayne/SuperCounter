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
import com.plus.server.dal.EverydayInfoDAO;
import com.plus.server.model.EverydayInfo;

@Service
public class EverydayInfoService {
	private static final Logger log = LoggerFactory.getLogger(EverydayInfoService.class);

	@Autowired
	private EverydayInfoDAO objectEverydayInfoDAO;

	public PageInfo<EverydayInfo> selectByModel(EverydayInfo objectEverydayInfo, Integer page, Integer pageSize) {
		log.info("查询日常信息，objectEverydayInfo=" + JSON.toJSONString(objectEverydayInfo));
		if(page == null || page <= 0){
			page = PageDefault.PAGE_NUM_DEFAULT;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = PageDefault.PAGE_SIZE_DEFAULT;
		}
		if(objectEverydayInfo.getValid() == null){
			objectEverydayInfo.setValid(1);
		}
		PageHelper.startPage(page, pageSize);
		List<EverydayInfo> orderList = this.objectEverydayInfoDAO.selectByModelLike(objectEverydayInfo);
		PageInfo<EverydayInfo> pageInfo = new PageInfo<EverydayInfo>(orderList);
		return pageInfo;
	}
	public List<EverydayInfo> selectByModel(EverydayInfo objectEverydayInfo) {
		log.info("查询日常信息，objectEverydayInfo=" + JSON.toJSONString(objectEverydayInfo));
		if(objectEverydayInfo.getValid() == null){
			objectEverydayInfo.setValid(1);
		}
		List<EverydayInfo> orderList = this.objectEverydayInfoDAO.selectByModelLike(objectEverydayInfo);
		return orderList;
	}
	
	public EverydayInfo selectById(Long id) throws Exception{
		log.info("查询日常信息，id=" + id);
		if(id == null){
			throw new Exception("参数为null");
		}
		return this.objectEverydayInfoDAO.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void saveOrUpdate(EverydayInfo objectEverydayInfo) {
		log.info("新增或更新日常信息，objectEverydayInfo=" + JSON.toJSONString(objectEverydayInfo));
		Date curDate= new Date();
		if(objectEverydayInfo.getId() == null){
			objectEverydayInfo.setValid(1);
			objectEverydayInfo.setGmtCreate(curDate);
			this.objectEverydayInfoDAO.insert(objectEverydayInfo);
		}else{
			objectEverydayInfo.setGmtModify(curDate);
			this.objectEverydayInfoDAO.updateByPrimaryKeySelective(objectEverydayInfo);
		}
	}
}
