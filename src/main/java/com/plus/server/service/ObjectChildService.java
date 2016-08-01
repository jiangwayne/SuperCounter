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
import com.plus.server.dal.ObjectChildDAO;
import com.plus.server.model.ObjectChild;

@Service
public class ObjectChildService {
	private static final Logger log = LoggerFactory.getLogger(ObjectChildService.class);

	@Autowired
	private ObjectChildDAO objectChildDAO;

	public PageInfo<ObjectChild> selectByModel(ObjectChild objectChild, Integer page, Integer pageSize) {
		log.info("查询子件，objectChild=" + JSON.toJSONString(objectChild));
		if(page == null || page <= 0){
			page = PageDefault.PAGE_NUM_DEFAULT;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = PageDefault.PAGE_SIZE_DEFAULT;
		}
		if(objectChild.getValid() == null){
			objectChild.setValid(1);
		}
		PageHelper.startPage(page, pageSize);
		List<ObjectChild> orderList = this.objectChildDAO.selectByModelLike(objectChild);
		PageInfo<ObjectChild> pageInfo = new PageInfo<ObjectChild>(orderList);
		return pageInfo;
	}
	public List<ObjectChild> selectByModel(ObjectChild objectChild) {
		log.info("查询子件，objectChild=" + JSON.toJSONString(objectChild));
		if(objectChild.getValid() == null){
			objectChild.setValid(1);
		}
		List<ObjectChild> orderList = this.objectChildDAO.selectByModelLike(objectChild);
		return orderList;
	}
	
	public ObjectChild selectById(Long id) throws Exception{
		log.info("查询子件，id=" + id);
		if(id == null){
			throw new Exception("参数为null");
		}
		return this.objectChildDAO.selectByPrimaryKey(id);
	}
	public List<ObjectChild> selectByIds(List<Long> idList) throws Exception{
		log.info("查询子件，idList=" + idList);
		if(idList == null){
			throw new Exception("参数为null");
		}
		return this.objectChildDAO.selectByIds(idList);
	}
	
	@Transactional
	public void saveOrUpdate(ObjectChild objectChild) {
		log.info("新增或更新子件，objectChild=" + JSON.toJSONString(objectChild));
		Date curDate= new Date();
		if(objectChild.getId() == null){
			objectChild.setValid(1);
			objectChild.setGmtCreate(curDate);
			this.objectChildDAO.insert(objectChild);
			//生成编码
			ObjectChild param = new ObjectChild();
			param.setId(objectChild.getId());
			param.setQrCode("oc"+objectChild.getId());
			objectChild.setGmtModify(curDate);
			this.objectChildDAO.updateByPrimaryKeySelective(param);
		}else{
			objectChild.setQrCode(null);//不更新编码
			objectChild.setGmtModify(curDate);
			this.objectChildDAO.updateByPrimaryKeySelective(objectChild);
		}
	}
}
