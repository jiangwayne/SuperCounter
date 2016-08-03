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
import com.plus.server.model.DailyOrder;
import com.plus.server.model.ObjectParent;
import com.plus.server.model.Organization;
import com.plus.server.service.AssignTaskService;
import com.plus.server.service.DailyOrderService;
import com.plus.server.service.ObjectParentService;
import com.plus.server.service.OrganizationService;

@Controller
@RequestMapping(value = "/gtb/dailyOrder")
public class DailyOrderController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(DailyOrderController.class);
	
	@Autowired
	private DailyOrderService dailyOrderService;
	@Autowired
	private AssignTaskService assignTaskService;
	@Autowired
	private ObjectParentService objectParentService;
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model, Long counterOrgId, Long objParentId, Long supplyOrgId, 
			Integer page, Integer pageSize) {
		ModelAndView mv = new ModelAndView("dailyOrderList.ftl");
		model.addAttribute("counterOrgId", counterOrgId);
		model.addAttribute("objParentId", objParentId);
		model.addAttribute("supplyOrgId", supplyOrgId);

		Organization o = new Organization();
		o.setValid(1);
		o.setType("2");// //组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
		List<Organization> counterList = assignTaskService.selectCounterByModel(o);
		model.addAttribute("counterList", counterList);
		o.setType("3");//3：供应商
		List<Organization> supplyList = assignTaskService.selectCounterByModel(o);
		model.addAttribute("supplyList", supplyList);
		ObjectParent objectParentParam = new ObjectParent();
		objectParentParam.setValid(1);
		List<ObjectParent> objParentList = objectParentService.selectByModel(objectParentParam);
		model.addAttribute("objParentList", objParentList);
		
		DailyOrder dailyOrder = new DailyOrder();
		dailyOrder.setValid(1);
		dailyOrder.setOrgCounterId(counterOrgId);
		dailyOrder.setObjParentId(objParentId);
		dailyOrder.setSupplyOrgId(supplyOrgId);
		PageInfo<DailyOrder> pageInfo = dailyOrderService.selectByModel(dailyOrder,page,pageSize);
		model.addAttribute("list", pageInfo.getList());
		model.addAttribute("pages",pageInfo.getPages());
		model.addAttribute("page",pageInfo.getPageNum());
		model.addAttribute("total",pageInfo.getTotal());
		return mv;
	}

	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(Model model, Long id) {
		ModelAndView mv = new ModelAndView("dailyOrderSaveOrUpdate.ftl");
		Organization o = new Organization();
		o.setValid(1);
		o.setType("2");// //组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
		List<Organization> counterList = assignTaskService.selectCounterByModel(o);
		model.addAttribute("counterList", counterList);
		ObjectParent objectParentParam = new ObjectParent();
		objectParentParam.setValid(1);
		List<ObjectParent> objParentList = objectParentService.selectByModel(objectParentParam);
		model.addAttribute("objParentList", objParentList);
		if(id != null){
			DailyOrder dailyOrder = null;
			try {
				dailyOrder = dailyOrderService.selectById(id);
			} catch (Exception e) {
				log.error("",e);
			}
			model.addAttribute("oc", dailyOrder);
		}
		return mv;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	public ModelAndView saveOrUpdate(Model model, Long id, Long orgCounterId,
			String comment,Long objParentId, Integer objParentCount) {
		Long supplyOrgId = null;
		ObjectParent op = null;
		try {
			op = objectParentService.selectById(objParentId);
		} catch (Exception e) {
			log.error("" , e);
		}
		if(op != null && op.getOrgId() != null){
			supplyOrgId = op.getOrgId();
		}
		DailyOrder dailyOrder = new DailyOrder();
		dailyOrder.setId(id);
		dailyOrder.setOrgCounterId(orgCounterId);
		dailyOrder.setComment(comment);
		dailyOrder.setObjParentId(objParentId);
		dailyOrder.setSupplyOrgId(supplyOrgId);
		dailyOrder.setObjParentCount(objParentCount);
		dailyOrderService.saveOrUpdate(dailyOrder);
		return list(model, null, null, null,null,null);
	}
	@RequestMapping(value = "/delete")
	@ResponseBody
	public BaseResp delete(Model model, Long id) {
		BaseResp ret = new BaseResp();
		DailyOrder dailyOrder = new DailyOrder();
		dailyOrder.setId(id);
		dailyOrder.setValid(-1);
		dailyOrderService.saveOrUpdate(dailyOrder);
		ret.setSuccess(true);
		return ret;
	}
	@RequestMapping(value = "/selectSupplyNameByObjParentId")
	@ResponseBody
	public BaseResp selectSupplyNameByObjParentId(Model model, Long objParentId) {
		BaseResp ret = new BaseResp();
		try {
			ObjectParent op = objectParentService.selectById(objParentId);
			if(op != null && op.getOrgId() != null){
				Organization org = organizationService.getOrg(op.getOrgId());
				ret.setMsg(org.getName());
			}
		} catch (Exception e) {
			log.error("", e);
		}
		ret.setSuccess(true);
		return ret;
	}

}
