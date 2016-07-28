package com.plus.server.controller;

import com.github.pagehelper.PageInfo;
import com.plus.server.model.OrderCounter;
import com.plus.server.model.OrderCounterDetail;
import com.plus.server.model.OrderSupplier;
import com.plus.server.model.Organization;
import com.plus.server.service.AssignTaskService;
import com.plus.server.service.OrderCounterService;
import com.plus.server.service.OrderSupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by mahui on 16/7/27.
 */
@Controller
@RequestMapping(value = "/gtb/orderCounter")
public class OrderCounterController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(OrderCounterController.class);

    @Autowired
    private OrderCounterService orderCounterService;
    @Autowired
    private AssignTaskService assignTaskService;

    @RequestMapping(value = "/list")
    public ModelAndView list(Model model, Long counterId, Integer page, Integer pageSize) {
        ModelAndView mv = new ModelAndView("orderCounterList.ftl");

        Organization brand = (Organization)this.httpSession.getAttribute("brand");
        Organization o = new Organization();
        o.setValid(1);
        o.setType("2");// //组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
        o.setBrandIds(brand.getId()+"");
        List<Organization> counterList = assignTaskService.selectCounterByModel(o);
        model.addAttribute("counterList", counterList);
        model.addAttribute("counterId", counterId);

        OrderCounter orderCounter = new OrderCounter();
        orderCounter.setValid(1);
        orderCounter.setOrgCounterId(counterId);
        PageInfo<OrderCounter> pageInfo = orderCounterService.selectByModel(orderCounter,page,pageSize);
        List<OrderCounter> list = pageInfo.getList();
        model.addAttribute("list", list);
        model.addAttribute("pages",pageInfo.getPages());
        model.addAttribute("page",pageInfo.getPageNum());
        model.addAttribute("total",pageInfo.getTotal());
        return mv;
    }

    @RequestMapping(value = "/toEditDtl")
    public ModelAndView toEditDtl(Model model, Long orderCounterId) {
        ModelAndView mv = new ModelAndView("orderCounterDtl.ftl");

        model.addAttribute("orderSupplierId", orderCounterId);
        try {
            OrderCounter orderCounter = orderCounterService.selectById(orderCounterId);
            model.addAttribute("orderCounter", orderCounter);
            List<OrderCounterDetail> dtlList = orderCounterService.selectDtlByPid(orderCounterId);
            model.addAttribute("dtlList", dtlList);
        } catch (Exception e) {
            log.error("", e);
        }
        return mv;
    }
}
