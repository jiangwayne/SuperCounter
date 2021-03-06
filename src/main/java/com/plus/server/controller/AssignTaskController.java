package com.plus.server.controller;

import java.util.Date;
import java.util.List;

import com.plus.server.common.util.DateUtil;
import com.plus.server.model.*;
import com.plus.server.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.plus.server.common.vo.resp.BaseResp;
import com.plus.server.common.vo.resp.DataResp;

@Controller
@RequestMapping(value = "/gtb/assignTask")
public class AssignTaskController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(AssignTaskController.class);

	@Autowired
	private AssignTaskService assignTaskService;
	@Autowired
	private ObjectParentService objectParentService;
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private UserService userService;

    @Autowired
    private OrderCounterService orderCounterService;

    @Autowired
    private OrderSetupService orderSetupService;

	@RequestMapping(value = "/toAssign")
	public ModelAndView toAssign() {
		ModelAndView mv = new ModelAndView("assignTask.ftl");
		return mv;
	}

	@RequestMapping(value = "/queryFur")
	@ResponseBody
	public DataResp queryFur(String furName) {
		DataResp ret = new DataResp();
		try {
			Furniture fur = new Furniture();
			fur.setValid(1);
			fur.setName(furName);
			List<Furniture> furList = assignTaskService.selectFurByModel(fur);
			ret.setData(furList);
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}

	@RequestMapping(value = "/queryObjParentByFur")
	@ResponseBody
	public DataResp queryObjParentByFur(@RequestParam(required = false) String objParentName,
			@RequestParam(required = false) Long furId) {
		DataResp ret = new DataResp();
		try {
			List<ObjectParent> list = Lists.newArrayList();
			if (furId == null || furId == -1L) {
				ObjectParent objParent = new ObjectParent();
				objParent.setValid(1);
				List<Integer> typeList = Lists.newArrayList();
				typeList.add(1);
				typeList.add(2);
				objParent.setTypeList(typeList);// 父件类型(1：图片，2：道具，3：灯片)
				objParent.setName(objParentName);
				list = objectParentService.selectByModel(objParent);
			} else {
				List<ObjectParent> list2 = organizationService.loadObjParentByFurId(furId);
				if (list2 != null && list2.size() > 0) {
					for (ObjectParent objectParent : list2) {
						if (objParentName == null || "".equals(objParentName.trim())) {
							list.add(objectParent);
						} else if (objectParent.getName() != null && objectParent.getName().indexOf(objParentName) >= 0) {
							list.add(objectParent);
						}
					}

				}
			}
			ret.setData(list);
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}

	@RequestMapping(value = "/queryCounter")
	@ResponseBody
	public DataResp queryCounter(String counterName) {
		DataResp ret = new DataResp();
		try {
			Organization o = new Organization();
			o.setValid(1);
			o.setType("2");// //组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
			o.setName(counterName);
			List<Organization> list = assignTaskService.selectCounterByModel(o);
			ret.setData(list);
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}

	@RequestMapping(value = "/queryObjParentByCounter")
	@ResponseBody
	public DataResp queryObjParentByCounter(String objParentName, Long counterId) {
		DataResp ret = new DataResp();
		try {
			List<ObjectParent> list = null;
			if (counterId == null || counterId == -1L) {
				ObjectParent objParent = new ObjectParent();
				objParent.setValid(1);
				objParent.setType(3);// 父件类型(1：图片，2：道具，3：灯片)
				objParent.setName(objParentName);
				list = objectParentService.selectByModel(objParent);
			} else {
//				List<CounterDetails> counterDtlList = organizationService.getCounterDtl(counterId);
//				if (counterDtlList != null && counterDtlList.size() > 0) {
//					list = Lists.newArrayList();
//					for (CounterDetails counterDetails : counterDtlList) {
//						ObjectParent op = counterDetails.getObjectParent();
//						if (op != null) {
//							if (objParentName == null || "".equals(objParentName.trim())) {
//								list.add(op);
//							} else if (op.getName() != null && op.getName().indexOf(objParentName) >= 0) {
//								list.add(op);
//							}
//						}
//					}
//				}
                ObjectParent objParent = new ObjectParent();
                objParent.setValid(1);
                objParent.setCounterId(counterId);
                objParent.setType(3);// 父件类型(1：图片，2：道具，3：灯片)
                objParent.setName(objParentName);
                list = objectParentService.selectByModel(objParent);
			}
			ret.setData(list);
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}

	/**
	 * 通过父件id查询供应商
	 */
	@RequestMapping(value = "/querySupplierByObjParentId")
	@ResponseBody
	public DataResp querySupplierByObjParentId(Long objParentId) {
		DataResp ret = new DataResp();
		try {
			ObjectParent objParent = objectParentService.selectById(objParentId);
			if (objParent != null && objParent.getOrgId() != null) {
				Organization supplier = this.organizationService.getOrg(objParent.getOrgId());
				ret.setData(supplier);
			}
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}

	/**
	 * 通过家具id查询柜台
	 */
	@RequestMapping(value = "/queryCounterByFurId")
	@ResponseBody
	public DataResp queryCounterByFurId(Long furId) {
		DataResp ret = new DataResp();
		try {
			List<CounterDetails> counterList = assignTaskService.queryCounterByFurId(furId);
			ret.setData(counterList);
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}
	/**
	 * 分配
	 */
	@RequestMapping(value = "/doAssign")
	@ResponseBody
	public BaseResp doAssign(String assignValueArrStr) {
		BaseResp ret = new BaseResp();
        System.out.println(assignValueArrStr);
        log.debug("mahui_debug",assignValueArrStr);
		try {
			System.out.println(assignValueArrStr);
			if(assignValueArrStr != null){
				String[] arr = assignValueArrStr.split(",");
				if(arr != null  && arr.length > 0){
					long[][] orderDtlArr = new long[arr.length][4];
					for (int i = 0; i < arr.length; i++) {
						String[] subArr = arr[i].split("_");
						if(subArr != null  && subArr.length == 4){
							orderDtlArr[i][0] = Long.parseLong(subArr[0]);
							orderDtlArr[i][1] = Long.parseLong(subArr[1]);
							orderDtlArr[i][2] = Long.parseLong(subArr[2]);
							orderDtlArr[i][3] = Long.parseLong(subArr[3]);
						}
					}
                    User u = (User)this.httpSession.getAttribute("user");
                    Organization org = (Organization)this.httpSession.getAttribute("brand");
					assignTaskService.doAssign(orderDtlArr,org.getId(), u.getId());
				}
			}
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}

	@RequestMapping(value="/allocateErectorTask",method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView allocateErectorTask(Model model, Long id, Long counterId, Integer taskType,
											String orderCounterIds, Long userSetupId,
											String setupTime, String comment) {
		ModelAndView mv = new ModelAndView("task/allocateErectorTask.ftl");
		id = id == null ? 0: id;
		String requestMethod = httpRequest.getMethod();
		if(requestMethod.equals("POST")){
			Date t = DateUtil.toDate(setupTime);
			assignTaskService.saveOrderSetup(id, counterId,taskType,orderCounterIds,userSetupId,t,comment);
			mv = new ModelAndView("task/listErectorTask.ftl");
			model.addAttribute("list", assignTaskService.listErectorTask(""));
			return mv;
		} else if(requestMethod.equals("GET")){
			model.addAttribute("counterList", organizationService.getCounterList(""));
			model.addAttribute("userList",userService.getUserList(6l, ""));
			model.addAttribute("counterOrderList",assignTaskService.listCounterOrder(""));
			if(id>0) {
				model.addAttribute("model", assignTaskService.getOrderSetup(id));
			}
		}
		return mv;
	}

//	@RequestMapping(value="/listErectorTask", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView listErectorTask(Model model, String keyword) {
//		ModelAndView mv = new ModelAndView("task/listErectorTask.ftl");
//		model.addAttribute("list", assignTaskService.listErectorTask(keyword));
//		return mv;
//	}
//
//    @RequestMapping(value = "/toDisplayErectorDtl")
//    public ModelAndView toEditDtl(Model model, Long orderSetupId) {
//        ModelAndView mv = new ModelAndView("task/erectorTaskDetail.ftl");
//
//        model.addAttribute("orderSupplierId", orderSetupId);
//        try {
//            OrderSetup orderSetup = orderSetupService.selectById(orderSetupId);
//            model.addAttribute("orderSetup", orderSetup);
//            List<OrderCounterDetail> dtlList = orderSetupService.selectDtlById(orderCounterId);
//            model.addAttribute("dtlList", dtlList);
//        } catch (Exception e) {
//            log.error("", e);
//        }
//        return mv;
//    }
}
