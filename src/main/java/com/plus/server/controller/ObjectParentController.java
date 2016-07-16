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
import com.plus.server.model.ObjectChild;
import com.plus.server.model.ObjectParent;
import com.plus.server.model.Organization;
import com.plus.server.model.ParentChild;
import com.plus.server.service.ObjectChildService;
import com.plus.server.service.ObjectParentService;

@Controller
@RequestMapping(value = "/gtb/parent")
public class ObjectParentController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ObjectParentController.class);

	@Autowired
	private ObjectParentService objectParentService;
	@Autowired
	private ObjectChildService objectChildService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model, String name, String qrCode, Integer page, Integer pageSize) {
		ModelAndView mv = new ModelAndView("parentList.ftl");
		model.addAttribute("name", name);
		model.addAttribute("qrCode", qrCode);

		ObjectParent objectParent = new ObjectParent();
		if(name != null  && !"".equals(name))
			objectParent.setName(name);
		if(qrCode != null  && !"".equals(qrCode))
			objectParent.setQrCode(qrCode);
		PageInfo<ObjectParent> pageInfo = objectParentService.selectByModel(objectParent,page,pageSize);
		model.addAttribute("list", pageInfo.getList());
		model.addAttribute("pages",pageInfo.getPages());
		model.addAttribute("page",pageInfo.getPageNum());
		model.addAttribute("total",pageInfo.getTotal());
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(Model model, Long id) {
		ModelAndView mv = new ModelAndView("parentSaveOrUpdate.ftl");
		Organization org = new Organization();
		org.setType("3");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
		List<Organization> orgList = objectParentService.selectOrg(org);
		org.setType("1");
		List<Organization> brandList = objectParentService.selectOrg(org);
		model.addAttribute("orgList", orgList);
		model.addAttribute("brandList",brandList);
		if(id != null){
			ObjectParent objectParent = null;
			try {
				objectParent = objectParentService.selectById(id);
			} catch (Exception e) {
				log.error("",e);
			}
			model.addAttribute("oc", objectParent);
			ObjectChild objectChild = new ObjectChild();
			List<ObjectChild> childList = objectChildService.selectByModel(objectChild);
			List<ParentChild> relChildList = null;
			try {
				relChildList = objectParentService.selectChildByParentId(id);
			} catch (Exception e) {
				log.error("", e);
			}
			model.addAttribute("childList", childList);
			model.addAttribute("relChildList", relChildList);
		}
		return mv;
	}
	
	@RequestMapping(value = "/addChildRel")
	@ResponseBody
	public BaseResp addChildRel(Model model, Long parentId, Long childId, Integer count) {
		BaseResp ret = new BaseResp();
		try {
			objectParentService.addChildRel(parentId,childId,count);
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}
	@RequestMapping(value = "/deleteChildRel")
	@ResponseBody
	public BaseResp deleteChildRel(Model model, Long parentId, Long id) {
		BaseResp ret = new BaseResp();
		try {
			objectParentService.deleteChildRel(id);
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	public ModelAndView saveOrUpdate(Model model, ObjectParent objectParent) {
		objectParentService.saveOrUpdate(objectParent);
		return list(model, null, null,null,null);
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public BaseResp delete(Model model, Long id) {
		BaseResp ret = new BaseResp();
		ObjectParent objectParent = new ObjectParent();
		objectParent.setId(id);
		objectParent.setValid(-1);
		objectParentService.saveOrUpdate(objectParent);
		ret.setSuccess(true);
		return ret;
	}

}
