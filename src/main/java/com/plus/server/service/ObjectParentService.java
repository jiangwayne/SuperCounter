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
import com.plus.server.dal.ObjectParentDAO;
import com.plus.server.dal.OrganizationDAO;
import com.plus.server.dal.ParentChildDAO;
import com.plus.server.model.ObjectParent;
import com.plus.server.model.Organization;
import com.plus.server.model.ParentChild;

@Service
public class ObjectParentService {
	private static final Logger log = LoggerFactory.getLogger(ObjectParentService.class);

	@Autowired
	private ObjectParentDAO objectParentDAO;
	@Autowired
	private OrganizationDAO organizationDAO;
	@Autowired
	private ParentChildDAO parentChildDAO;
	@Autowired
	private ObjectChildDAO objectChildDAO;

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
	
	public List<ObjectParent> selectByModel(ObjectParent objectParent) {
		log.info("查询父件，objectParent=" + JSON.toJSONString(objectParent));
		if(objectParent.getValid() == null){
			objectParent.setValid(1);
		}
		List<ObjectParent> orderList = this.objectParentDAO.selectByModelLike(objectParent);
		return orderList;
	}
	
	public List<Organization> selectOrg(Organization org) {
		log.info("查询组织，org=" + JSON.toJSONString(org));
		if(org.getValid() == null){
			org.setValid(1);
		}
		List<Organization> orgList = this.organizationDAO.selectByModelLike(org);
		return orgList;
	}
	
	public ObjectParent selectById(Long id) throws Exception{
		log.info("查询父件，id=" + id);
		if(id == null){
			throw new Exception("参数为null");
		}
		return this.objectParentDAO.selectByPrimaryKey(id);
	}
	
	public List<ParentChild> selectChildByParentId(Long parentId) throws Exception{
		log.info("查询父件下的子件，parentId=" + parentId);
		if(parentId == null){
			throw new Exception("参数为null");
		}
		ParentChild param = new ParentChild();
		param.setValid(1);
		param.setObjParentId(parentId);
		return this.parentChildDAO.selectByModel(param);
	}
	
	@Transactional(rollbackFor = Exception.class)
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

	public void addChildRel(Long parentId, Long childId, Integer count) throws Exception{
		ParentChild param = new ParentChild();
		param.setValid(1);
		param.setObjChildId(childId);
		param.setObjParentId(parentId);
		List<ParentChild> relList = parentChildDAO.selectByModel(param);
		if(relList != null && relList.size() > 0){
			throw new Exception("关系已存在");
		}
		ParentChild rel = new ParentChild();
		rel.setGmtCreate(new Date());
		rel.setObjChildCount(count);
		rel.setObjChildId(childId);
		rel.setObjParentId(parentId);
		rel.setValid(1);
		parentChildDAO.insert(rel);
		
	}

	public void deleteChildRel(Long id) {
		ParentChild rel = new ParentChild();
		rel.setId(id);
		rel.setValid(-1);
		rel.setGmtModify(new Date());
		parentChildDAO.updateByPrimaryKeySelective(rel);
	}

}
