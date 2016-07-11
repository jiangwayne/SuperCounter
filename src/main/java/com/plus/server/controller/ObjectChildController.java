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
import com.plus.server.model.ObjectChild;
import com.plus.server.service.ObjectChildService;

@Controller
@RequestMapping(value = "/gtb/child")
public class ObjectChildController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ObjectChildController.class);

	@Autowired
	private ObjectChildService objectChildService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model, String name, String qrCode, Integer page, Integer pageSize) {
		ModelAndView mv = new ModelAndView("childList.ftl");
		model.addAttribute("name", name);
		model.addAttribute("qrCode", qrCode);

		ObjectChild objectChild = new ObjectChild();
		if(name != null  && !"".equals(name))
			objectChild.setName(name);
		if(qrCode != null  && !"".equals(qrCode))
			objectChild.setQrCode(qrCode);
		PageInfo<ObjectChild> pageInfo = objectChildService.selectByModel(objectChild,page,pageSize);
		model.addAttribute("list", pageInfo.getList());
		model.addAttribute("pages",pageInfo.getPages());
		model.addAttribute("page",pageInfo.getPageNum());
		model.addAttribute("total",pageInfo.getTotal());
		return mv;
	}

	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(Model model, Long id) {
		ModelAndView mv = new ModelAndView("childSaveOrUpdate.ftl");
		if(id != null){
			ObjectChild objectChild = null;
			try {
				objectChild = objectChildService.selectById(id);
			} catch (Exception e) {
				log.error("",e);
			}
			model.addAttribute("oc", objectChild);
		}
		return mv;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	public ModelAndView saveOrUpdate(Model model, Long id, String name,
			String remark,String qrCode, Integer length, Integer width,
			Integer height, String picUrl) {
		ObjectChild objectChild = new ObjectChild();
		objectChild.setId(id);
		objectChild.setName(name);
		objectChild.setQrCode(qrCode);
		objectChild.setLength(length);
		objectChild.setHeight(height);
		objectChild.setRemark(remark);
		objectChild.setWidth(width);
		objectChild.setPicUrl(picUrl);
		objectChildService.saveOrUpdate(objectChild);
		return list(model, null, null,null,null);
	}
	@RequestMapping(value = "/delete")
	@ResponseBody
	public BaseResp delete(Model model, Long id) {
		BaseResp ret = new BaseResp();
		ObjectChild objectChild = new ObjectChild();
		objectChild.setId(id);
		objectChild.setValid(-1);
		objectChildService.saveOrUpdate(objectChild);
		ret.setSuccess(true);
		return ret;
	}

}
