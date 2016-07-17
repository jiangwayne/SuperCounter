package com.plus.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.plus.server.model.OrderSupplier;
import com.plus.server.model.Organization;
import com.plus.server.service.AssignTaskService;
import com.plus.server.service.OrderSupplierService;

@Controller
@RequestMapping(value = "/gtb/orderSupplier")
public class OrderSupplierController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(OrderSupplierController.class);

	@Autowired
	private OrderSupplierService orderSupplierService;
	@Autowired
	private AssignTaskService assignTaskService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model, Long supplierId, Integer page, Integer pageSize) {
		ModelAndView mv = new ModelAndView("orderSupplierList.ftl");
		
		Organization o = new Organization();
		o.setValid(1);
		o.setType("3");// //组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
		List<Organization> supplierList = assignTaskService.selectCounterByModel(o);
		model.addAttribute("supplierList", supplierList);
		model.addAttribute("supplierId", supplierId);

		OrderSupplier orderSupplier = new OrderSupplier();
		orderSupplier.setValid(1);
		orderSupplier.setOrgSupplierId(supplierId);
		PageInfo<OrderSupplier> pageInfo = orderSupplierService.selectByModel(orderSupplier,page,pageSize);
		List<OrderSupplier> list = pageInfo.getList();
		model.addAttribute("list", list);
		model.addAttribute("pages",pageInfo.getPages());
		model.addAttribute("page",pageInfo.getPageNum());
		model.addAttribute("total",pageInfo.getTotal());
		return mv;
	}


}
