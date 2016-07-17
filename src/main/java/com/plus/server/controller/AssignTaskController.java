package com.plus.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.plus.server.common.vo.resp.BaseResp;
import com.plus.server.common.vo.resp.DataResp;
import com.plus.server.model.CounterDetails;
import com.plus.server.model.Furniture;
import com.plus.server.model.ObjectParent;
import com.plus.server.model.Organization;
import com.plus.server.service.AssignTaskService;
import com.plus.server.service.ObjectParentService;
import com.plus.server.service.OrganizationService;

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
				List<CounterDetails> counterDtlList = organizationService.getCounterDtl(counterId);
				if (counterDtlList != null && counterDtlList.size() > 0) {
					list = Lists.newArrayList();
					for (CounterDetails counterDetails : counterDtlList) {
						ObjectParent op = counterDetails.getObjectParent();
						if (op != null) {
							if (objParentName == null || "".equals(objParentName.trim())) {
								list.add(op);
							} else if (op.getName() != null && op.getName().indexOf(objParentName) >= 0) {
								list.add(op);
							}
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
			List<Organization> counterList = assignTaskService.queryCounterByFurId(furId);
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
					assignTaskService.doAssign(orderDtlArr);
				}
			}
		} catch (Exception e) {
			ret.setMsg(e.getMessage());
			return ret;
		}
		ret.setSuccess(true);
		return ret;
	}

}
