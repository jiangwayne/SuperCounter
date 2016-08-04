package com.plus.server.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plus.server.common.util.BeanMapper;
import com.plus.server.common.vo.ObjectParentVo;
import com.plus.server.common.vo.StockVo;
import com.plus.server.common.vo.resp.BaseResp;
import com.plus.server.model.CounterDetails;
import com.plus.server.model.Furniture;
import com.plus.server.model.ObjectChild;
import com.plus.server.model.ObjectParent;
import com.plus.server.model.Organization;
import com.plus.server.model.ParentChild;
import com.plus.server.service.ObjectChildService;
import com.plus.server.service.ObjectParentService;
import com.plus.server.service.OrganizationService;

@Controller
@RequestMapping(value = "/gtb/parent")
public class ObjectParentController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ObjectParentController.class);

	@Autowired
	private ObjectParentService objectParentService;
	@Autowired
	private ObjectChildService objectChildService;
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model, String name, String qrCode,String orgId,String type, Integer page, Integer pageSize) {
		ModelAndView mv = new ModelAndView("parentList.ftl");
		model.addAttribute("name", name);
		model.addAttribute("qrCode", qrCode);
		model.addAttribute("orgId", qrCode);
		model.addAttribute("type", type);

		ObjectParent objectParent = new ObjectParent();
		if(name != null  && !"".equals(name))
			objectParent.setName(name);
		if(qrCode != null  && !"".equals(qrCode))
			objectParent.setQrCode(qrCode);
		if(orgId != null  && !"".equals(orgId))
			objectParent.setOrgId(Long.parseLong(orgId));
		if(type != null  && !"".equals(type)) {
			objectParent.setType(Integer.parseInt(type));
		}

		PageInfo<ObjectParent> pageInfo = objectParentService.selectByModel(objectParent,page,pageSize);
//		Organization org = new Organization();
//		org.setType("3");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
//		List<Organization> orgList = objectParentService.selectOrg(org);
		if(pageInfo != null){
			List<ObjectParent> opList = pageInfo.getList();
			if(opList != null && opList.size() > 0){
				List<Long> orgIdList = Lists.newArrayList();
				for(ObjectParent vo : opList){
					orgIdList.add(vo.getOrgId());
				}
				Map<Long,Organization> orgMap = Maps.newHashMap();
				if(orgIdList != null && orgIdList.size() > 0){
					orgMap = selectOrgByIds(orgIdList);
				}
				for(ObjectParent vo : opList){
					Organization org = orgMap.get(vo.getOrgId());
					if(org != null){
						vo.setOrg(org);
					}
				}
				model.addAttribute("list", opList);
			}
			
			model.addAttribute("pages",pageInfo.getPages());
			model.addAttribute("page",pageInfo.getPageNum());
			model.addAttribute("total",pageInfo.getTotal());
		}
		return mv;
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
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(Model model, Long id) {
		ModelAndView mv = new ModelAndView("parentSaveOrUpdate.ftl");
		Organization org = new Organization();
		org.setType("3");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
		List<Organization> orgList = objectParentService.selectOrg(org);
		org.setType("1");
		List<Organization> brandList = objectParentService.selectOrg(org);
		org.setType("2");
		List<Organization> counterList = objectParentService.selectOrg(org);
		model.addAttribute("orgList", orgList);
		model.addAttribute("brandList",brandList);
		model.addAttribute("counterList",counterList);
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

	@RequestMapping(value = "/toEditCounterParent")
	public ModelAndView toEditCounterParent(Model model, Long id,Long counterId) {
		ModelAndView mv = new ModelAndView("parentCounterAdd.ftl");
		Organization org = new Organization();
		org.setType("3");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
		List<Organization> orgList = objectParentService.selectOrg(org);
		org.setType("1");
		List<Organization> brandList = objectParentService.selectOrg(org);
		model.addAttribute("orgList", orgList);
		model.addAttribute("brandList",brandList);
		org.setType("2");
		org.setId(counterId);
		List<Organization> counterList = objectParentService.selectOrg(org);
		model.addAttribute("counterList",counterList);
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
		return list(model, null, null,null,null,null,null);
	}

	@RequestMapping(value = "/saveOrUpdateByCounter")
	public ModelAndView saveOrUpdateByCounter(Model model, ObjectParent objectParent) {
		objectParentService.saveOrUpdate(objectParent);


		ModelAndView mv = new ModelAndView("organization/addCounter.ftl");
		String requestMethod = httpRequest.getMethod();

			model.addAttribute("brandList", organizationService.getBrandList(""));
			model.addAttribute("styleList", organizationService.getCounterStyleList(""));

		    Organization counter = organizationService.getOrg(objectParent.getCounterId());
		    model.addAttribute("model", counter);
				if(counter != null  && counter.getParentId() != null){
					List<Furniture> furList = organizationService.getFurnitureList2(counter.getParentId(), null);
					model.addAttribute("furList", furList);
				}
				List<CounterDetails> counterDtlList = organizationService.getCounterDtl(objectParent.getCounterId());
				model.addAttribute("counterDtlList", counterDtlList);
				System.out.println(JSON.toJSONString(counterDtlList));

                ObjectParent op = new ObjectParent();
                op.setCounterId(objectParent.getCounterId());
                List<ObjectParent> parentList = objectParentService.selectByModel(op);
                model.addAttribute("parentList", parentList);
                System.out.println(JSON.toJSONString(parentList));
		return mv;

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
