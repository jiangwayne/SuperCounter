package com.plus.server.controller;

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
 * Created by jiangwulin on 16/7/3.
 */
@Controller
@Api("用户")
@RequestMapping(value = "gtb/user")
public class UserController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

//    @RequestMapping(value = "add", method = {RequestMethod.GET,RequestMethod.POST})
//    public ModelAndView addUser(String name, String pwd, String roleId, String orgId) {
//        ModelAndView mv = new ModelAndView("add.ftl");
//        log.info("name={},pwd={}",name,pwd);
//        if(name != null) {
//            userService.addUser(name, pwd, Long.parseLong(roleId), Long.parseLong(orgId));
//            mv = new ModelAndView("list.ftl");
//        }
//        return mv;
//    }

    @RequestMapping(value = "/listContactStaff", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listContactStaff(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("user/listContactStaff.ftl");
        model.addAttribute("list", userService.getUserList(2l, keyword));
        return mv;
    }


    @RequestMapping(value = "/addContactStaff", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addContactStaff(Model model, Long id, String name, String pwd, String roleId, String orgId) {
        ModelAndView mv = new ModelAndView("user/addContactStaff.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
//        if(requestMethod.equals("POST")){
//            userService.saveUser(id, orgId, mediaType, styleId,
//                    name,  address,  longLat,  phone, counterNo,  comment);
//            mv = new ModelAndView("organization/listCounter.ftl");
//            model.addAttribute("list", organizationService.getCounterList(""));
//            return mv;
//        } else if(requestMethod.equals("GET")){
//            model.addAttribute("brandList", organizationService.getBrandList(""));
//            model.addAttribute("styleList", organizationService.getCounterStyleList(""));
//            if(id>0) {
//                model.addAttribute("model", organizationService.getOrg(id));
//            }
//        }
        return mv;
    }


    @RequestMapping(value = "/listBA", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listBA(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("user/listBA.ftl");
        model.addAttribute("list", userService.getUserList(5l, keyword));
        return mv;
    }

    @RequestMapping(value = "/addBA", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addBA(String name, String pwd, String roleId, String orgId) {
        ModelAndView mv = new ModelAndView("user/addBA.ftl");
        if(name != null) {
            userService.addUser(name, pwd, Long.parseLong(roleId), Long.parseLong(orgId));
            mv = new ModelAndView("user/listContactStaff.ftl");
        }
        return mv;
    }

    @RequestMapping(value = "/listBrandManager", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listBrandManager(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("user/listBrandManager.ftl");
        model.addAttribute("list", userService.getUserList(3l, keyword));
        return mv;
    }

    @RequestMapping(value = "/addBrandManage", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addBrandManage(String name, String pwd, String roleId, String orgId) {
        ModelAndView mv = new ModelAndView("user/addBrandManage.ftl");
        if(name != null) {
            userService.addUser(name, pwd, Long.parseLong(roleId), Long.parseLong(orgId));
            mv = new ModelAndView("user/listBrandManage.ftl");
        }
        return mv;
    }

    @RequestMapping(value = "/listErector", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listErector(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("user/listErector.ftl");
        model.addAttribute("list", userService.getUserList(6l, keyword));
        return mv;
    }

    @RequestMapping(value = "/addErector", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addErector(String name, String pwd, String roleId, String orgId) {
        ModelAndView mv = new ModelAndView("user/addErector.ftl");
        log.info("name={},pwd={}",name,pwd);
        if(name != null) {
            userService.addUser(name, pwd, Long.parseLong(roleId), Long.parseLong(orgId));
            mv = new ModelAndView("user/listErector.ftl");
        }
        return mv;
    }

    @RequestMapping(value = "/listSupplier", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listSupplier(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("user/listSupplier.ftl");
        log.info("listContactStaff keyword={}",keyword);
        model.addAttribute("list", userService.getUserList(4l, keyword));
        return mv;
    }


    @RequestMapping(value = "/addSupplier", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addSupplier(String name, String pwd, String roleId, String orgId) {
        ModelAndView mv = new ModelAndView("user/addSupplier.ftl");
        log.info("name={},pwd={}",name,pwd);
        if(name != null) {
            userService.addUser(name, pwd, Long.parseLong(roleId), Long.parseLong(orgId));
            mv = new ModelAndView("user/listSupplier.ftl");
        }
        return mv;
    }

}
