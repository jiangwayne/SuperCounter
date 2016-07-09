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
import com.plus.server.dal.ObjectParentDAO;
import com.plus.server.model.ObjectParent;

@Service
public class ObjectParentService {
	private static final Logger log = LoggerFactory.getLogger(ObjectParentService.class);

	@Autowired
	private ObjectParentDAO objectParentDAO;

	@Transactional
	public PageInfo<ObjectParent> selectByModel(ObjectParent objectParent, Integer page, Integer pageSize) {
		log.info("查询父件，objectParent=" + JSON.toJSONString(objectParent));
		if(page == null || page <= 0){
			page = PageDefault.PAGE_NUM_DEFAULT;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = PageDefault.PAGE_SIZE_DEFAULT;
		}
		if(objectParent.getValid() == null){
			objectParent.setValid(1);
		}
		PageHelper.startPage(page, pageSize);
		List<ObjectParent> orderList = this.objectParentDAO.selectByModelLike(objectParent);
		PageInfo<ObjectParent> pageInfo = new PageInfo<ObjectParent>(orderList);
		return pageInfo;
	}
	
	@Transactional
	public ObjectParent selectById(Long id) throws Exception{
		log.info("查询父件，id=" + id);
		if(id == null){
			throw new Exception("参数为null");
		}
		return this.objectParentDAO.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void saveOrUpdate(ObjectParent objectParent) {
		log.info("新增或更新父件，objectParent=" + JSON.toJSONString(objectParent));
		Date curDate= new Date();
		if(objectParent.getId() == null){
			objectParent.setValid(1);
			objectParent.setGmtCreate(curDate);
			this.objectParentDAO.insert(objectParent);
			//生成编码
			ObjectParent param = new ObjectParent();
			param.setId(objectParent.getId());
			param.setQrCode("of"+objectParent.getId());
			objectParent.setGmtModify(curDate);
			this.objectParentDAO.updateByPrimaryKeySelective(param);
		}else{
			objectParent.setQrCode(null);//不更新编码
			objectParent.setGmtModify(curDate);
			this.objectParentDAO.updateByPrimaryKeySelective(objectParent);
		}
	}

}
