package com.plus.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;

@Controller
@Api("示例")
@RequestMapping(value = "/gtb")
public class IndexController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/top", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView top(Model model, String name, String city, String birthdayDate) {
		ModelAndView mv = new ModelAndView("include/top.ftl");
		return mv;
	}
	
	@RequestMapping(value = "/menu", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView menu(Model model, String name, String city, String birthdayDate) {
		ModelAndView mv = new ModelAndView("include/menu.ftl");
		return mv;
	}
	
	@RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView index(Model model, String name, String city, String birthdayDate) {
		ModelAndView mv = new ModelAndView("index.ftl");
		return mv;
	}

}
