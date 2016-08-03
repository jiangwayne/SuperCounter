package com.plus.server.controller;

import com.plus.server.model.Organization;
import com.plus.server.model.User;
import com.plus.server.model.Organization;
import com.plus.server.model.UserRole;
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
 * Created by jiangwulin on 16/7/3.
 */
@Controller
@Api("登录")
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(Model model, String name, String pwd, String brandId) {
        ModelAndView mv = new ModelAndView("user/login.ftl");
        //ModelAndView mv = new ModelAndView("index.ftl");
        if(name == null || name.equals("") || pwd == null || pwd.equals("")) {
            model.addAttribute("brandList", organizationService.getBrandList(""));
            return mv;
        }
        else {
            log.info("name={},pwd={}",name,pwd);
            //return mv;
            User u = userService.login(name,pwd);
            Organization org = organizationService.getOrg(new Long(brandId));
            if(u != null){
                UserRole userRole = userService.getUserRole(u.getId());
                this.httpSession.setAttribute("user", u);
                this.httpSession.setAttribute("brand", org);
                this.httpSession.setAttribute("userRole", userRole);
                mv = new ModelAndView("redirect:/gtb/index.ftl");
            }else {
                model.addAttribute("brandList", organizationService.getBrandList(""));
            }
        }
        return mv;
    }

    @RequestMapping(value = "/loginOut",method = {RequestMethod.GET})
    public ModelAndView loginOut(Model model) {
        ModelAndView mv = new ModelAndView("index.ftl");
        this.httpSession.removeAttribute("user");
        this.httpSession.removeAttribute("brand");
        return mv;
    }

}
