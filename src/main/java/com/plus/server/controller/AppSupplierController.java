package com.plus.server.controller;



import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.github.pagehelper.PageInfo;
import com.plus.server.common.util.RetUtil;
import com.plus.server.model.*;
import com.plus.server.service.*;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mahui on 16/7/16.
 */
@Controller
@Api("登录")
@RequestMapping(value = "/app")
public class AppSupplierController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ObjectParentService objectParentService;

    @Autowired
    private ObjectChildService objectChildService;

    @Autowired
    private AssignTaskService assignTaskService;
    @RequestMapping(value = "/userLogin", method = {RequestMethod.GET})
    public JSONObject userLogin(String name, String pwd) {
        ModelAndView mv = new ModelAndView("user/login.ftl");
        if(name == null || name.equals("") || pwd == null || pwd.equals("")) {
            return null;
        }
        else {
            log.info("name={},pwd={}",name,pwd);
            mv = new ModelAndView("redirect:/gtb/index.ftl");

            //return mv;
            User u = userService.login(name,pwd);

            JSONObject retJobj = RetUtil.createBaseRetJsonObj();

            retJobj.getJSONObject("data").put("username", u.getName());
            retJobj.getJSONObject("data").put("password", pwd);
            retJobj.getJSONObject("data").put("orgId", u.getOrgId());
            retJobj.getJSONObject("data").put("brandId","333");
            retJobj.getJSONObject("data").put("descriptaion", u.getComment());
            retJobj.getJSONObject("data").put("name", u.getFullName());
            retJobj.getJSONObject("data").put("email", u.getPhone());
            return retJobj;

        }

    }

    //根据供应商id,查询父件
    @RequestMapping(value = "/supplierObjectParent", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<ObjectParent> supplierObjectParent(Long OrgId, String keyword) {

        List<ObjectParent> result = new ArrayList<>();
        ObjectParent objectParent = new ObjectParent();
        objectParent.setValid(1);
        if(OrgId > 0) {
            objectParent.setOrgId(OrgId);
        }
        List<ObjectParent> list = this.objectParentService.selectByModel(objectParent);
        for(ObjectParent o : list){
            if(keyword == null || keyword.equals("") || o.getId().toString().contains(keyword) || o.getName().contains(keyword)
                    || o.getObjParentNo().contains(keyword)){
                result.add(o);
            }
        }
        return result;
    }

    //根据父件id,查询子件
    @RequestMapping(value = "/supplierObjectParentC", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<ParentChild> supplierObjectParentC(Long parentId) {

        List<ParentChild> relChildList = null;
        try {
            relChildList = objectParentService.selectChildByParentId(parentId);
        } catch (Exception e) {
            log.error("", e);
        }
        return relChildList;
    }


    //查询子件
    @RequestMapping(value = "/objectChild", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ObjectChild objectChild(Long childId) {

        ObjectChild objectChild = null;
        try {
            objectChild = objectChildService.selectById(childId);
        } catch (Exception e) {
            log.error("", e);
        }
        return objectChild;
    }

    @RequestMapping(value="/listErectorTask", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Map<String, String>> listErectorTask(String keyword) {
        return assignTaskService.listErectorTask(keyword);
    }

}
