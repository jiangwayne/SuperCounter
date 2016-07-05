package com.plus.server.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plus.server.model.User;
import com.plus.server.service.DemoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@Api("示例")
@RequestMapping(value = "gtb")
public class DemoController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoService demoService;

	private static List<Map<String,String>> userList = Lists.newArrayList();
	static{
		userList.add(createUser("张三", "上海", "19850101"));
		userList.add(createUser("李四", "湖南", "19991201"));
	}
	private static Map<String,String> createUser(String name, String city, String birthdayDate){
		Map<String,String> m = Maps.newHashMap();
		m.put("name", name);
		m.put("city", city);
		m.put("birthdayDate", birthdayDate);
		return m;
	}
	@RequestMapping(value = "/userList", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView userList(Model model, String name, String city, String birthdayDate) {
		ModelAndView mv = new ModelAndView("demo.ftl");
		log.info("name={},city={},birthdayDate={}",name,city,birthdayDate);
		if(name!= null && city!= null && birthdayDate!= null )
			userList.add(createUser(name, city, birthdayDate));
		model.addAttribute("list", userList);
		return mv;
	}
	
}
