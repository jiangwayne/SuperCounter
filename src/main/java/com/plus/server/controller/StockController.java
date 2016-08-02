package com.plus.server.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plus.server.common.util.BeanMapper;
import com.plus.server.common.vo.StockVo;
import com.plus.server.model.ObjectChild;
import com.plus.server.model.ObjectParent;
import com.plus.server.model.Organization;
import com.plus.server.model.Stock;
import com.plus.server.service.ObjectChildService;
import com.plus.server.service.ObjectParentService;
import com.plus.server.service.OrganizationService;
import com.plus.server.service.StockService;

@Controller
@RequestMapping(value = "/gtb/stock")
public class StockController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockService stockService;
	@Autowired
	private ObjectChildService objectChildService;
	@Autowired
	private ObjectParentService objectParentService;
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model,Integer type, String name, Integer page, Integer pageSize) {
		log.info("库存查询，type={}，name={}",type,name);
		ModelAndView mv = new ModelAndView("stockList.ftl");
		model.addAttribute("type", type);
		model.addAttribute("name", name);
		if(type == null){
			type = 1;
		}
		PageInfo<Stock> pageInfo = null;
		if(type == 1){//1：父件，2：子件
			pageInfo = listParent(name, page, pageSize);
		}else if(type == 2){
			pageInfo = listChild(name, page, pageSize);
		}
		if(pageInfo != null ){
			model.addAttribute("pages", pageInfo.getPages());
			model.addAttribute("page", pageInfo.getPageNum());
			model.addAttribute("total", pageInfo.getTotal());
			if( pageInfo.getList() != null){
				List<StockVo> list = BeanMapper.mapList(pageInfo.getList(), StockVo.class);
				List<Long> childIdList = Lists.newArrayList();
				List<Long> parentIdList = Lists.newArrayList();
				List<Long> orgIdList = Lists.newArrayList();
				for(StockVo vo : list){
					if(vo.getGoodType() == 1){//1：父件，2：子件
						parentIdList.add(vo.getGoodId());
					}else if(vo.getGoodType() == 2){//1：父件，2：子件
						childIdList.add(vo.getGoodId());
					}
					orgIdList.add(vo.getOrgId());
				}
				Map<Long,ObjectChild> childMap = Maps.newHashMap();
				Map<Long,ObjectParent> parentMap = Maps.newHashMap();
				Map<Long,Organization> orgMap = Maps.newHashMap();
				if(childIdList != null && childIdList.size() > 0){
					childMap = selectChildByIds(childIdList);
				}
				if(parentIdList != null && parentIdList.size() > 0){
					parentMap = selectParentByIds(parentIdList);
				}
				if(orgIdList != null && orgIdList.size() > 0){
					orgMap = selectOrgByIds(orgIdList);
				}
				for(StockVo vo : list){
					if(vo.getGoodType() == 1){//1：父件，2：子件
						ObjectParent op = parentMap.get(vo.getGoodId());
						if(op != null){
							vo.setGoodName(op.getName());
							vo.setPicUrl(op.getPicUrl());
						}
					}else if(vo.getGoodType() == 2){//1：父件，2：子件
						ObjectChild op = childMap.get(vo.getGoodId());
						if(op != null){
							vo.setGoodName(op.getName());
							vo.setPicUrl(op.getPicUrl());
						}
					}
					Organization org = orgMap.get(vo.getOrgId());
					if(org != null){
						vo.setOrgName(org.getName());
					}
				}
				model.addAttribute("list", list);
			}
		}
		return mv;
	}
	
	private Map<Long,ObjectChild> selectChildByIds(List<Long> idList){
		Map<Long,ObjectChild> childMap = Maps.newHashMap();
		List<ObjectChild> list = null;
		try {
			list = objectChildService.selectByIds(idList);
		} catch (Exception e) {
			log.error("", e);
		}
		if(list != null && list.size() > 0){
			for (ObjectChild objectChild : list) {
				childMap.put(objectChild.getId(), objectChild);
			}
		}
		return childMap;
	}
	private Map<Long,ObjectParent> selectParentByIds(List<Long> idList){
		Map<Long,ObjectParent> childMap = Maps.newHashMap();
		List<ObjectParent> list = null;
		try {
			list = objectParentService.selectByIds(idList);
		} catch (Exception e) {
			log.error("", e);
		}
		if(list != null && list.size() > 0){
			for (ObjectParent objectChild : list) {
				childMap.put(objectChild.getId(), objectChild);
			}
		}
		return childMap;
	}
	private Map<Long,Organization> selectOrgByIds(List<Long> idList){
		Map<Long,Organization> childMap = Maps.newHashMap();
		List<Organization> list = null;
		try {
			list = organizationService.selectByIds(idList);
		} catch (Exception e) {
			log.error("", e);
		}
		if(list != null && list.size() > 0){
			for (Organization objectChild : list) {
				childMap.put(objectChild.getId(), objectChild);
			}
		}
		return childMap;
	}
	
	private PageInfo<Stock> listChild(String childName, Integer page, Integer pageSize) {
		Stock stock = new Stock();
		stock.setGoodType(2);// 货物类型（1：父件，2：子件）
		stock.setValid(1);
		if (childName != null && !"".equals(childName)) {
			List<Long> goodIdList = Lists.newArrayList();

			ObjectChild oc = new ObjectChild();
			oc.setValid(1);
			oc.setName(childName);
			List<ObjectChild> ocList = objectChildService.selectByModel(oc);
			if (ocList != null && ocList.size() > 0) {
				for (ObjectChild objectChild : ocList) {
					goodIdList.add(objectChild.getId());
				}
			} else {
				goodIdList.add(-1L);
			}
			stock.setGoodIdList(goodIdList);
		}
		PageInfo<Stock> pageInfo = stockService.selectByModel(stock, page, pageSize);
		return pageInfo;
	}

	private PageInfo<Stock> listParent(String parentName, Integer page, Integer pageSize) {
		Stock stock = new Stock();
		stock.setGoodType(1);// 货物类型（1：父件，2：子件）
		stock.setValid(1);
		if (parentName != null && !"".equals(parentName)) {
			List<Long> goodIdList = Lists.newArrayList();

			ObjectParent oc = new ObjectParent();
			oc.setValid(1);
			oc.setName(parentName);
			List<ObjectParent> ocList = objectParentService.selectByModel(oc);
			if (ocList != null && ocList.size() > 0) {
				for (ObjectParent objectParent : ocList) {
					goodIdList.add(objectParent.getId());
				}
			} else {
				goodIdList.add(-1L);
			}
			stock.setGoodIdList(goodIdList);
		}
		PageInfo<Stock> pageInfo = stockService.selectByModel(stock, page, pageSize);
		return pageInfo;
	}

}
