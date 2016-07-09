package com.plus.server.controller;

import com.plus.server.model.Organization;
import com.plus.server.service.OrganizationService;
import com.plus.server.service.UserService;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jiangwulin on 16/7/5.
 */
@Controller
@Api("组织")
@RequestMapping(value = "gtb/org")
public class OrganizationController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);
    @Autowired
    private OrganizationService organizationService;

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
                model.addAttribute("model", organizationService.getFurniture(id));
            }
        }
        return mv;
    }
}
