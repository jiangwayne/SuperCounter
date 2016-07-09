package com.plus.server.controller;

import java.util.List;

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
import com.plus.server.model.DisplayHandbook;
import com.plus.server.model.ObjectParent;
import com.plus.server.service.DisplayHandbookService;
import com.plus.server.service.ObjectParentService;

@Controller
@RequestMapping(value = "gtb/displayHandbook")
public class DisplayHandbookController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(DisplayHandbookController.class);

	@Autowired
	private DisplayHandbookService objectDisplayHandbookService;
	@Autowired
	private ObjectParentService objectParentService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model, String name, Integer page, Integer pageSize) {
		ModelAndView mv = new ModelAndView("displayHandbookList.ftl");
		model.addAttribute("name", name);

		DisplayHandbook objectDisplayHandbook = new DisplayHandbook();
		if(name != null  && !"".equals(name))
			objectDisplayHandbook.setName(name);
		PageInfo<DisplayHandbook> pageInfo = objectDisplayHandbookService.selectByModel(objectDisplayHandbook,page,pageSize);
		model.addAttribute("list", pageInfo.getList());
		model.addAttribute("pages",pageInfo.getPages());
		model.addAttribute("page",pageInfo.getPageNum());
		model.addAttribute("total",pageInfo.getTotal());
		return mv;
	}

	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(Model model, Long id) {
		ModelAndView mv = new ModelAndView("displayHandbookSaveOrUpdate.ftl");
		//父件列表
		ObjectParent objectParent = new ObjectParent();
		objectParent.setValid(1);
		List<ObjectParent> objParentList = objectParentService.selectByModel(objectParent);
		model.addAttribute("objParentList", objParentList);
		//1级陈列手册列表
		DisplayHandbook displayHandbook = new DisplayHandbook();
		displayHandbook.setValid(1);
		displayHandbook.setParentId(0L);
		List<DisplayHandbook> displayHandbookList = objectDisplayHandbookService.selectByModel(displayHandbook);
		model.addAttribute("displayHandbookList", displayHandbookList);
		if(id != null){
			DisplayHandbook objectDisplayHandbook = null;
			try {
				objectDisplayHandbook = objectDisplayHandbookService.selectById(id);
			} catch (Exception e) {
				log.error("",e);
			}
			model.addAttribute("oc", objectDisplayHandbook);
		}
		return mv;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	public ModelAndView saveOrUpdate(Model model, Long id, String name,
			String description,Long objParentId,Long parentId, String picUrl) {
		DisplayHandbook objectDisplayHandbook = new DisplayHandbook();
		objectDisplayHandbook.setId(id);
		objectDisplayHandbook.setName(name);
		objectDisplayHandbook.setDescription(description);
		objectDisplayHandbook.setObjParentId(objParentId);
		objectDisplayHandbook.setParentId(parentId);
		objectDisplayHandbook.setPicUrl(picUrl);
		objectDisplayHandbookService.saveOrUpdate(objectDisplayHandbook);
		return list(model, null,null,null);
	}
	@RequestMapping(value = "/delete")
	@ResponseBody
	public BaseResp delete(Model model, Long id) {
		BaseResp ret = new BaseResp();
		DisplayHandbook objectDisplayHandbook = new DisplayHandbook();
		objectDisplayHandbook.setId(id);
		objectDisplayHandbook.setValid(-1);
		objectDisplayHandbookService.saveOrUpdate(objectDisplayHandbook);
		ret.setSuccess(true);
		return ret;
	}

}
