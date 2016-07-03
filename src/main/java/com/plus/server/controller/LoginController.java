package com.plus.server.controller;

import com.plus.server.model.User;
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
@RequestMapping(value = "login")
public class LoginController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(String name, String pwd) {
        ModelAndView mv = new ModelAndView("user/login.ftl");
        if(name.equals("") || pwd.equals("")) {
            return mv;
        }
        else {
            log.info("name={},pwd={}",name,pwd);
            User u = userService.login(name,pwd);
            if(u != null){
                this.httpSession.setAttribute("user", u);
                mv = new ModelAndView("index.ftl");
            }
        }
        return mv;
    }


}
