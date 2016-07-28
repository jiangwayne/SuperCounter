package com.plus.server.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.plus.server.service.ObjectParentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.plus.server.common.vo.resp.BaseResp;
import com.plus.server.common.vo.resp.LoadObjParentByFurIdResp;
import com.plus.server.model.CounterDetails;
import com.plus.server.model.Furniture;
import com.plus.server.model.ObjectParent;
import com.plus.server.model.Organization;
import com.plus.server.service.OrganizationService;
import com.wordnik.swagger.annotations.Api;

/**
 * Created by jiangwulin on 16/7/5.
 */
@Controller
@Api("组织")
@RequestMapping(value = "/gtb/org")
public class OrganizationController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ObjectParentService objectParentService;

    @RequestMapping(value = "/listBrand", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listBrand(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("organization/listBrand.ftl");
        model.addAttribute("list", organizationService.getBrandList(keyword));
        return mv;
    }

    @RequestMapping(value = "/addBrand", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addBrand(Model model, Long id, String name, String phone, String wx, String email,  String comment) {
        ModelAndView mv = new ModelAndView("organization/addBrand.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            organizationService.saveBrand(id, name, phone, wx, email, comment);
            mv = new ModelAndView("organization/listBrand.ftl");
            model.addAttribute("list", organizationService.getBrandList(""));
            return mv;
        } else if(requestMethod.equals("GET")){
            if(id>0) {
                model.addAttribute("model", organizationService.getOrg(id));
            }
        }
        return mv;
    }

    @RequestMapping(value = "/addCounterType", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addCounterType(Model model, Long id, Long orgId, String name,  String comment) {
        ModelAndView mv = new ModelAndView("organization/addCounterType.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            organizationService.saveCounterStyle(id, orgId, name,comment);
            mv = new ModelAndView("organization/listCounterType.ftl");
            model.addAttribute("list", organizationService.getCounterStyleList(""));
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("brandList", organizationService.getBrandList(""));
            if(id>0) {
                model.addAttribute("model", organizationService.getCounterStyle(id));
                model.addAttribute("counterFurnitureList", organizationService.getCounterFurnitureMap(id));
            }
        }
        return mv;
    }


    @RequestMapping(value = "/listCounterType", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listCounterType(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("organization/listCounterType.ftl");
        model.addAttribute("list", organizationService.getCounterStyleList(keyword));
        return mv;
    }


    @RequestMapping(value = "/listCounter", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listCounter(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("organization/listCounter.ftl");
        model.addAttribute("list", organizationService.getCounterList(keyword));
        return mv;
    }


    @RequestMapping(value = "/addCounter", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addCounter(Model model, Long id, Long orgId, String mediaType, Long styleId,
                                   String name, String address, String longLat, String phone,
                                   String counterNo, String comment) {
        ModelAndView mv = new ModelAndView("organization/addCounter.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            organizationService.saveCounter(id, orgId, mediaType, styleId,
                     name,  address,  longLat,  phone, counterNo,  comment);
            mv = new ModelAndView("organization/listCounter.ftl");
            model.addAttribute("list", organizationService.getCounterList(""));
            return mv;
        }else{
            model.addAttribute("brandList", organizationService.getBrandList(""));
            model.addAttribute("styleList", organizationService.getCounterStyleList(""));
            if(id>0) {
            	Organization counter = organizationService.getOrg(id);
            	model.addAttribute("model", counter);
            	if(counter != null  && counter.getParentId() != null){
            		List<Furniture> furList = organizationService.getFurnitureList2(counter.getParentId(), null);
            		model.addAttribute("furList", furList);
            	}
            	List<CounterDetails> counterDtlList = organizationService.getCounterDtl(id);
            	model.addAttribute("counterDtlList", counterDtlList);
            	System.out.println(JSON.toJSONString(counterDtlList));

                ObjectParent op = new ObjectParent();
                op.setCounterId(id);
                List<ObjectParent> parentList = objectParentService.selectByModel(op);
                model.addAttribute("parentList", parentList);
                System.out.println(JSON.toJSONString(parentList));
            }
        }
        return mv;
    }
    @RequestMapping(value = "/loadObjParentByFurId", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public LoadObjParentByFurIdResp loadObjParentByFurId(Long furId) {
    	LoadObjParentByFurIdResp ret = new LoadObjParentByFurIdResp();
    	List<ObjectParent> objParentList = organizationService.loadObjParentByFurId(furId);
    	ret.setObjParentList(objParentList);
    	ret.setSuccess(true);
    	return ret;
	}
    @RequestMapping(value = "/addOrUpdateCounterDtl", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public BaseResp addOrUpdateCounterDtl(Long dtlId,Long counterOrgId, Long furId, Long objParentId, String siteNo) {
    	BaseResp ret = new BaseResp();
    	Date now = new Date();
    	CounterDetails d = new CounterDetails();
    	d.setId(dtlId);
    	d.setOrgId(counterOrgId);
    	d.setFurnitureId(furId);
    	d.setObjParentId(objParentId);
    	d.setSiteNo(siteNo);
    	d.setValid(1);
    	if(dtlId == null)
    		d.setGmtCreate(now);
    	else
    		d.setGmtModify(now);
    	organizationService.addOrUpdateCounterDtl(d);
    	
    	ret.setSuccess(true);
    	return ret;
	}
    @RequestMapping(value = "/deleteCounterDtl", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public BaseResp deleteCounterDtl(Long dtlId) {
    	BaseResp ret = new BaseResp();
    	if(dtlId == null || dtlId <= 0){
    		ret.setMsg("参数错误");
    		return ret;
    	}
    	
    	Date now = new Date();
    	CounterDetails d = new CounterDetails();
    	d.setId(dtlId);
    	d.setValid(-1);
    	d.setGmtModify(now);
        objectParentService.deleteChildRel(dtlId);
    	ret.setSuccess(true);
    	return ret;
	}
    @RequestMapping(value = "/addCounterDetail", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addCounterDetail(Model model, Long id, Long orgId, String mediaType, Long styleId,
                                   String name, String address, String longLat, String phone,
                                   String counterNo, String comment) {
        ModelAndView mv = new ModelAndView("organization/addCounter.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            organizationService.saveCounter(id, orgId, mediaType, styleId,
                     name,  address,  longLat,  phone, counterNo,  comment);
            mv = new ModelAndView("organization/listCounter.ftl");
            model.addAttribute("list", organizationService.getCounterList(""));
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("brandList", organizationService.getBrandList(""));
            model.addAttribute("styleList", organizationService.getCounterStyleList(""));
            if(id>0) {
                model.addAttribute("model", organizationService.getOrg(id));
            }
        }
        return mv;
    }

    @RequestMapping(value = "/listFurniture", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listFurniture(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("organization/listFurniture.ftl");
        model.addAttribute("list", organizationService.getFurnitureList(keyword));
        return mv;
    }

    //根据品牌id,查询家具
    @RequestMapping(value = "/listFurniture2", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Furniture> listFurniture2(Long brandId, String keyword) {
        return organizationService.getFurnitureList2(brandId, keyword);
    }

    //根据品牌id,查询父件
    @RequestMapping(value = "/listObjParent", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<ObjectParent> listObjParent(Long brandId, String keyword) {
        return organizationService.getObjParent(brandId, keyword);
    }


    @RequestMapping(value = "/addFurniture", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addFurniture(Model model, Long id, Long orgId, String furnitureNo, String name,  String comment) {
        ModelAndView mv = new ModelAndView("organization/addFurniture.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            organizationService.saveFurniture(id, orgId, furnitureNo, name,comment);
            mv = new ModelAndView("organization/listFurniture.ftl");
            model.addAttribute("list", organizationService.getFurnitureList(""));
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("brandList", organizationService.getBrandList(""));
            if(id>0) {
                model.addAttribute("furnitureObjParentList", organizationService.getFurnitureObjParentMap(id));
                model.addAttribute("furnitureObjParentList", organizationService.getFurnitureObjParentMap(id));
                model.addAttribute("model", organizationService.getFurniture(id));
            }
        }
        return mv;
    }

    @RequestMapping(value = "/listSupplier", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listSupplier(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("organization/listSupplier.ftl");
        model.addAttribute("list", organizationService.getSupplierList(keyword));
        return mv;
    }

    @RequestMapping(value = "/listInstallationCompany", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listInstallationCompany(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("organization/listInstallationCompany.ftl");
        model.addAttribute("list", organizationService.getInstallationList(keyword));
        return mv;
    }


    @RequestMapping(value = "/addSupplier", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addSupplier(Model model, Long id, String brandIds, String name, String address,
                                    String longLat, String phone, String email, String comment) {
        ModelAndView mv = new ModelAndView("organization/addSupplier.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            organizationService.saveSupplier(id, brandIds, name,address,longLat,phone, email, comment);
            mv = new ModelAndView("organization/listSupplier.ftl");
            model.addAttribute("list", organizationService.getSupplierList(""));
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("brandList", organizationService.getBrandList(""));
            if(id>0) {
                model.addAttribute("model", organizationService.getOrg(id));
            }
        }
        return mv;
    }

    @RequestMapping(value = "/addInstallationCompany", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addInstallationCompany(Model model, Long id, String brandIds, String name, String address,
                                    String longLat, String phone, String email, String comment) {
        ModelAndView mv = new ModelAndView("organization/addInstallationCompany.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            organizationService.saveInstallation(id, brandIds, name,address,longLat,phone, email, comment);
            mv = new ModelAndView("organization/listInstallationCompany.ftl");
            model.addAttribute("list", organizationService.getInstallationList(""));
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("brandList", organizationService.getBrandList(""));
            if(id>0) {
                model.addAttribute("model", organizationService.getOrg(id));
            }
        }
        return mv;
    }


    @RequestMapping(value = "/addCounterTemplate", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Map<String,String>> addCounterTemplate(Long counterStyleId, String furnitureIds) {
        String[] furnitureIdArray = furnitureIds.split(",");
        for (int i=0; i<furnitureIdArray.length; i++){
            organizationService.addCounterTemplate(counterStyleId, Long.parseLong(furnitureIdArray[i]), 0l, 1);
        }

        return organizationService.getCounterFurnitureMap(counterStyleId);

    }

    @RequestMapping(value = "/addFurnitureTemplate", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Map<String,String>> addFurnitureTemplate(Long furnitureId, String objParentIds) {
        String[] objParentIdArray = objParentIds.split(",");
        for (int i=0; i<objParentIdArray.length; i++){
            organizationService.addCounterTemplate(0l, furnitureId, Long.parseLong(objParentIdArray[i]), 1);
        }

        return organizationService.getFurnitureObjParentMap(furnitureId);

    }

}
