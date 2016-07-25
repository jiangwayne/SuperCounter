package com.plus.server.controller;

import com.plus.server.service.Support;
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

import javax.swing.*;
import java.io.IOException;


/**
 * Created by jiangwulin on 16/7/3.
 */
@Controller
@Api("用户")
@RequestMapping(value = "/gtb/user")
public class UserController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/listContactStaff", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listContactStaff(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("user/listContactStaff.ftl");
        model.addAttribute("list", userService.getUserList(2l, keyword));
        return mv;
    }


    @RequestMapping(value = "/addContactStaff", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addContactStaff(Model model, Long id, String brandIds, String name, String pwd,
                                        String fullName, String phone, String comment)  {
        ModelAndView mv = new ModelAndView("user/addContactStaff.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            if(userService.saveUser(id, brandIds, 1l, name, pwd,
                    fullName, 2L, phone,  comment)) { //客服经理role=2,orgId=1(admin)
                mv = new ModelAndView("user/listContactStaff.ftl");
                model.addAttribute("list", userService.getUserList(2l, ""));
            } else{
                model.addAttribute("brandList", userService.getBrandList(""));
                JOptionPane.showMessageDialog(null, "用户名已存在");
            }

            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("brandList", userService.getBrandList(""));
            if(id>0) {
                model.addAttribute("model", userService.getUser(id));
            }
        }
        return mv;
    }


    @RequestMapping(value = "/listBA", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listBA(Model model,String keyword) {
        ModelAndView mv = new ModelAndView("user/listBA.ftl");
        model.addAttribute("list", userService.getUserList(5l, keyword));
        return mv;
    }

    @RequestMapping(value = "/addBA", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addBA(Model model, Long id, Long orgId, String name, String pwd,
                              String fullName, String phone, String comment) {
        ModelAndView mv = new ModelAndView("user/addBA.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            if(userService.saveUser(id, "", orgId,name,pwd,fullName,5l,phone,comment)){
                mv = new ModelAndView("user/listBA.ftl");
                model.addAttribute("list", userService.getUserList(5l, ""));
            } else{
                model.addAttribute("counterList", userService.getCounterList(""));
                JOptionPane.showMessageDialog(null, "用户名已存在");
            }
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("counterList", userService.getCounterList(""));
            if(id>0) {
                model.addAttribute("model", userService.getUser(id));
            }
        }
        return mv;
    }

    @RequestMapping(value = "/listBrandManager", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listBrandManager(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("user/listBrandManager.ftl");
        model.addAttribute("list", userService.getUserList(3l, keyword));
        return mv;
    }

    @RequestMapping(value = "/addBrandManager", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addBrandManage(Model model, Long id, String brandIds, String name, String pwd,
                                       String fullName, String phone, String comment)  {
        ModelAndView mv = new ModelAndView("user/addBrandManager.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            if(userService.saveUser(id, brandIds, 1l, name, pwd,
                     fullName, 3l, phone,  comment)){//品牌经理role=3,orgId=1(admin)
                mv = new ModelAndView("user/listBrandManager.ftl");
                model.addAttribute("list", userService.getUserList(3l, ""));
            }else{
                model.addAttribute("brandList", userService.getBrandList(""));
                JOptionPane.showMessageDialog(null, "用户名已存在");
            }
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("brandList", userService.getBrandList(""));
            if(id>0) {
                model.addAttribute("model", userService.getUser(id));
            }
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
    public ModelAndView addErector(Model model, Long id, Long orgId, String name,
                                   String pwd, String fullName,String phone,String comment) {
        ModelAndView mv = new ModelAndView("user/addErector.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            if(userService.saveUser(id, "", orgId,name,pwd,fullName,6l,phone,comment)) {
                mv = new ModelAndView("user/listErector.ftl");
                model.addAttribute("list", userService.getUserList(6l, ""));
            }else{
                model.addAttribute("erectorOrgList", userService.getOrganizationList("5",""));
                JOptionPane.showMessageDialog(null, "用户名已存在");
            }
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("erectorOrgList", userService.getOrganizationList("5",""));
            if(id>0) {
                model.addAttribute("model", userService.getUser(id));
            }
        }
        return mv;
    }

    @RequestMapping(value = "/listSupplier", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listSupplier(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("user/listSupplier.ftl");
        model.addAttribute("list", userService.getUserList(4l, keyword));
        return mv;
    }


    @RequestMapping(value = "/addSupplier", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addSupplier(Model model, Long id, String brandIds, Long orgId, String name, String pwd,
                                    String fullName, String phone, String comment) {
        ModelAndView mv = new ModelAndView("user/addSupplier.ftl");
        id = id == null ? 0: id;
        String requestMethod = httpRequest.getMethod();
        if(requestMethod.equals("POST")){
            if(userService.saveUser(id, brandIds, orgId, name,pwd,
                    fullName,4l,phone,comment)) {//品牌经理role=4,orgId=供应商id
                mv = new ModelAndView("user/listSupplier.ftl");
                model.addAttribute("list", userService.getUserList(4l, ""));
            }else{
                model.addAttribute("brandList", userService.getBrandList(""));
                model.addAttribute("supplierList", Support.getInstance().getOrganizationList("3",""));//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
                JOptionPane.showMessageDialog(null, "用户名已存在");
            }
            return mv;
        } else if(requestMethod.equals("GET")){
            model.addAttribute("brandList", userService.getBrandList(""));
            model.addAttribute("supplierList", Support.getInstance().getOrganizationList("3",""));//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
            if(id>0) {
                model.addAttribute("model", userService.getUser(id));
            }
        }
        return mv;
    }

}
