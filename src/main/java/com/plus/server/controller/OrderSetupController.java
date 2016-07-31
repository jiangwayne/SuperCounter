package com.plus.server.controller;

import com.github.pagehelper.PageInfo;
import com.plus.server.model.*;
import com.plus.server.service.AssignTaskService;
import com.plus.server.service.OrderCounterService;
import com.plus.server.service.OrderSetupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahui on 16/7/27.
 */
@Controller
@RequestMapping(value = "/gtb/orderSetup")
public class OrderSetupController  extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(OrderSetupController.class);

    @Autowired
    private OrderCounterService orderCounterService;

    @Autowired
    private OrderSetupService orderSetupService;

    @Autowired
    private AssignTaskService assignTaskService;


    @RequestMapping(value="/listErectorTask", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listErectorTask(Model model, String keyword) {
        ModelAndView mv = new ModelAndView("task/listErectorTask.ftl");
        model.addAttribute("list", assignTaskService.listErectorTask(keyword));
        return mv;
    }

    @RequestMapping(value = "/toDisplayErectorDtl")
    public ModelAndView toEditDtl(Model model, Long orderSetupId) {
        ModelAndView mv = new ModelAndView("task/erectorTaskDetail.ftl");

        model.addAttribute("orderSupplierId", orderSetupId);
        try {
            OrderSetup orderSetup = orderSetupService.selectById(orderSetupId);
            model.addAttribute("orderSetup", orderSetup);

            List<OrderCounterDetail> dtlListAll = new ArrayList<OrderCounterDetail>();
            String[] strIds = orderSetup.getOrderCounterIds().split(",");
            for (int i = 0; i < strIds.length; i++) {
                Long orderCounterId = Long.parseLong(strIds[i]);
                List<OrderCounterDetail> dtlList = orderCounterService.selectDtlByPid(orderCounterId);
                dtlListAll.addAll(dtlList);
            }
            log.info("orderSetup/toDisplayErectorDtl/dtlCountList",dtlListAll.toString());
            model.addAttribute("dtlCountList", dtlListAll);

            List<SetupRecord> setDtlList = orderSetupService.selectDtlById(orderSetupId);
            log.info("orderSetup/toDisplayErectorDtl/setDtlList"+dtlListAll.toString());
            model.addAttribute("setDtlList"+setDtlList);
        } catch (Exception e) {
            log.error("", e);
        }
        return mv;
    }


}
