package com.plus.server.controller;

import com.github.pagehelper.PageInfo;
import com.plus.server.model.Order;
import com.plus.server.model.OrderSupplier;
import com.plus.server.model.Organization;
import com.plus.server.service.AssignTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by mahui on 16/8/2.
 */
@Controller
@RequestMapping(value = "/gtb/order")
public class OrderController  extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private AssignTaskService assignTaskService;

    @RequestMapping(value = "/list")
    public ModelAndView list(Model model) {
        ModelAndView mv = new ModelAndView("orderList.ftl");

        Order o = new Order();
        o.setValid(1);
        List<Order> list = assignTaskService.selectOrder(o);

        model.addAttribute("list", list);
        return mv;
    }

}
