package com.plus.server.controller;

import com.google.common.collect.Lists;
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

import java.util.List;
import java.util.Map;

/**
 * Created by jiangwulin on 16/7/3.
 */
@Controller
@Api("用户")
@RequestMapping(value = "gtb/user")
public class UserController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static List<Map<String,String>> userList = Lists.newArrayList();
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userList", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView userList(Model model, String name, String pwd, String confirmPwd) {
        ModelAndView mv = new ModelAndView("user/list.ftl");
        log.info("name={},pwd={}",name,pwd);
        if(!name.equals("") && !pwd.equals("") && !confirmPwd.equals("") && pwd.equals(confirmPwd)) {
            userService.addUser(name,pwd);
        }

        model.addAttribute("list", userService.getUserList());
        return mv;
    }
}
