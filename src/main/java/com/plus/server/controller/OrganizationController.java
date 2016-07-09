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
 * Created by jiangwulin on 16/7/5.
 */
@Controller
@Api("组织")
@RequestMapping(value = "gtb/organization")
public class OrganizationController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView organizationList(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("list.ftl");
        log.info("keyword={}",keyword);
        model.addAttribute("list", userService.getOrganizationList(keyword));
        return mv;
    }


    @RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addOrganization(String name, String pwd, String roleId, String orgId) {
        ModelAndView mv = new ModelAndView("add.ftl");
        log.info("name={},pwd={}",name,pwd);
        if(name != null) {
            userService.addUser(name, pwd, Long.parseLong(roleId), Long.parseLong(orgId));
            mv = new ModelAndView("list.ftl");
        }
        return mv;
    }
}
