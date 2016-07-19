package com.plus.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.plus.server.common.vo.resp.BaseResp;
import com.plus.server.model.OrderSupplier;
import com.plus.server.model.OrderSupplierDetail;
import com.plus.server.model.Organization;
import com.plus.server.service.AssignTaskService;
import com.plus.server.service.OrderSupplierService;

@Controller
@RequestMapping(value = "/gtb/orderPrice")
public class OrderPriceController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(OrderPriceController.class);

	@Autowired
	private OrderSupplierService orderSupplierService;
	@Autowired
	private AssignTaskService assignTaskService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model, Long supplierId, Integer page, Integer pageSize) {
		ModelAndView mv = new ModelAndView("orderPriceList.ftl");
		
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


	@RequestMapping(value = "/toDtlList")
	public ModelAndView toDtlList(Model model, Long orderSupplierId) {
		ModelAndView mv = new ModelAndView("orderPriceDtlList.ftl");
		
		model.addAttribute("orderSupplierId", orderSupplierId);
		try {
			OrderSupplier orderSupplier = orderSupplierService.selectById(orderSupplierId);
			model.addAttribute("orderSupplier", orderSupplier);
			List<OrderSupplierDetail> dtlList = orderSupplierService.selectDtlByPid(orderSupplierId);
			model.addAttribute("dtlList", dtlList);
		} catch (Exception e) {
			log.error("", e);
		}
		return mv;
	}
	@RequestMapping(value = "/toEditDtl")
	public ModelAndView toEditDtl(Model model, Long dtlId, Long orderSupplierId) {
		ModelAndView mv = new ModelAndView("orderPriceDtl.ftl");
		
		model.addAttribute("dtlId", dtlId);
		model.addAttribute("orderSupplierId", orderSupplierId);
		try {
			OrderSupplierDetail orderSupplierDetail = orderSupplierService.selectDtlById(dtlId);
			model.addAttribute("orderSupplierDetail", orderSupplierDetail);
		} catch (Exception e) {
			log.error("", e);
		}
		return mv;
	}
	
	@RequestMapping(value = "/updateDtl")
	@ResponseBody
	public BaseResp updateDtl(Long dtlId, Integer priceCny, Integer priceUsd, Integer priceEur, Integer unitPriceCny,
			Integer unitPriceUsd, Integer unitPriceEur, Integer finalPriceCny, Integer finalPriceUsd,
			Integer finalPriceEur, Integer finalUnitPriceCny, Integer finalUnitPriceUsd, Integer finalUnitPriceEur,
			String poNumber, String comment) {
		BaseResp ret = new BaseResp();
		if(dtlId == null){
			ret.setMsg("id为空");
			return ret;
		}
		try {
			OrderSupplierDetail dtl = new OrderSupplierDetail();
			dtl.setId(dtlId);
			dtl.setPriceCny(priceCny);
			dtl.setPriceUsd(priceUsd);
			dtl.setPriceEur(priceEur);
			dtl.setUnitPriceCny(unitPriceCny);
			dtl.setUnitPriceUsd(unitPriceUsd);
			dtl.setUnitPriceEur(unitPriceEur);
			dtl.setFinalPriceCny(finalPriceCny);
			dtl.setFinalPriceUsd(finalPriceUsd);
			dtl.setFinalPriceEur(finalPriceEur);
			dtl.setFinalUnitPriceCny(finalUnitPriceCny);
			dtl.setFinalUnitPriceUsd(finalUnitPriceUsd);
			dtl.setFinalUnitPriceEur(finalUnitPriceEur);
			dtl.setPoNumber(poNumber);
			dtl.setComment(comment);
			orderSupplierService.updateDtl(dtl);
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}
	
}
