package com.plus.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.plus.server.common.vo.resp.BaseResp;
import com.plus.server.model.EverydayInfo;
import com.plus.server.service.EverydayInfoService;

@Controller
@RequestMapping(value = "/gtb/everydayInfo")
public class EverydayInfoController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(EverydayInfoController.class);

	@Autowired
	private EverydayInfoService objectEverydayInfoService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model, String name, Integer page, Integer pageSize) {
		ModelAndView mv = new ModelAndView("everydayInfoList.ftl");
		model.addAttribute("name", name);

		EverydayInfo objectEverydayInfo = new EverydayInfo();
		if(name != null  && !"".equals(name))
			objectEverydayInfo.setName(name);
		PageInfo<EverydayInfo> pageInfo = objectEverydayInfoService.selectByModel(objectEverydayInfo,page,pageSize);
		model.addAttribute("list", pageInfo.getList());
		model.addAttribute("pages",pageInfo.getPages());
		model.addAttribute("page",pageInfo.getPageNum());
		model.addAttribute("total",pageInfo.getTotal());
		return mv;
	}

	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(Model model, Long id) {
		ModelAndView mv = new ModelAndView("everydayInfoSaveOrUpdate.ftl");
		if(id != null){
			EverydayInfo objectEverydayInfo = null;
			try {
				objectEverydayInfo = objectEverydayInfoService.selectById(id);
			} catch (Exception e) {
				log.error("",e);
			}
			model.addAttribute("oc", objectEverydayInfo);
		}
		return mv;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	public ModelAndView saveOrUpdate(Model model, Long id, String name,
			String description, String picUrl) {
		EverydayInfo objectEverydayInfo = new EverydayInfo();
		objectEverydayInfo.setId(id);
		objectEverydayInfo.setName(name);
		objectEverydayInfo.setDescription(description);
		objectEverydayInfo.setPicUrl(picUrl);
		objectEverydayInfoService.saveOrUpdate(objectEverydayInfo);
		return list(model, null,null,null);
	}
	@RequestMapping(value = "/delete")
	@ResponseBody
	public BaseResp delete(Model model, Long id) {
		BaseResp ret = new BaseResp();
		EverydayInfo objectEverydayInfo = new EverydayInfo();
		objectEverydayInfo.setId(id);
		objectEverydayInfo.setValid(-1);
		objectEverydayInfoService.saveOrUpdate(objectEverydayInfo);
		ret.setSuccess(true);
		return ret;
	}

}
