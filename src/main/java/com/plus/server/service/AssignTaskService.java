package com.plus.server.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.plus.server.common.util.DateUtil;
import com.plus.server.dal.*;
import com.plus.server.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class AssignTaskService {
	private static final Logger log = LoggerFactory.getLogger(AssignTaskService.class);

	@Autowired
	private FurnitureDAO furnitureDAO;
	@Autowired
	private OrganizationDAO organizationDAO;
	@Autowired
	private CounterDetailsDAO counterDetailsDAO;
	
	@Autowired
	private OrderCounterDAO orderCounterDAO;
	@Autowired
	private OrderCounterDetailDAO orderCounterDetailDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private OrderSupplierDAO orderSupplierDAO;
	@Autowired
	private OrderSupplierDetailDAO orderSupplierDetailDAO;

	@Autowired
	private OrderSetupDAO orderSetupDAO;

	@Autowired
	private UserDAO userDAO;

	public List<Furniture> selectFurByModel(Furniture o) {
		List<Furniture> list = this.furnitureDAO.selectByModelLike(o);
		return list;
	}

	public List<Organization> selectCounterByModel(Organization o) {
		List<Organization> list = this.organizationDAO.selectByModelLike(o);
		return list;
	}

	public List<Organization> queryCounterByFurId(Long furId) {
		CounterDetails param = new CounterDetails();
		param.setValid(1);
		param.setFurnitureId(furId);
		List<CounterDetails> list = counterDetailsDAO.selectByModel(param);
		List<Long> counterIdList = Lists.newArrayList();
		if(list != null && list.size() > 0){
			for (CounterDetails c : list) {
				counterIdList.add(c.getOrgId());
			}
		}
		if(counterIdList != null && counterIdList.size() > 0){
			List<Organization> retList = organizationDAO.selectByIds(counterIdList);
			return retList;
		}
		return null;
	}

	@Transactional(rollbackFor = Exception.class)
	public void doAssign(long[][] orderDtlArr) {
		if(orderDtlArr == null || orderDtlArr.length == 0){
			return;
		}
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		//订单总表
		Order order = new Order();
		order.setGmtCreate(now);
		order.setValid(1);
		orderDAO.insert(order);
		String orderNo = "A"+f.format(now)+(order.getId() % 10000 + 10000);
		order.setOrderNo(orderNo);
		orderDAO.updateByPrimaryKeySelective(order);
		//柜台订单总表
		Map<Long,Object> counterIdMap = Maps.newHashMap();
		for (int i = 0; i < orderDtlArr.length; i++) {
			//counterId+"_"+objParentId+"_"+supplier.id+"_"+count;
			long counterId = orderDtlArr[i][0];
//			long objParentId = orderDtlArr[i][1];
//			long supplierId = orderDtlArr[i][2];
//			long count = orderDtlArr[i][3];
			if(!counterIdMap.containsKey(counterId)){
				counterIdMap.put(counterId, null);
			}
		}
		for(Map.Entry<Long,Object> entry : counterIdMap.entrySet()){
			OrderCounter orderCounter = new OrderCounter();
			orderCounter.setOrgCounterId(entry.getKey());
			orderCounter.setOrderId(order.getId());
			orderCounter.setGmtCreate(now);
			orderCounter.setValid(1);
			orderCounterDAO.insert(orderCounter);
			String orderCounterNo = "C"+f.format(now)+(order.getId() % 10000 + 10000);
			orderCounter.setOrderCounterNo(orderCounterNo);
			orderCounterDAO.updateByPrimaryKeySelective(orderCounter);
			//柜台订单明细
			List<OrderCounterDetail> cDtlList = Lists.newArrayList();
			for (int i = 0; i < orderDtlArr.length; i++) {
				long counterId = orderDtlArr[i][0];
				long objParentId = orderDtlArr[i][1];
				long supplierId = orderDtlArr[i][2];
				Long count = orderDtlArr[i][3];
				if(entry.getKey() == counterId){
					OrderCounterDetail orderCounterDetail = new OrderCounterDetail();
					orderCounterDetail.setOrderId(order.getId());
					orderCounterDetail.setOrderCounterId(orderCounter.getId());
					orderCounterDetail.setObjParentId(objParentId);
					orderCounterDetail.setObjParentCount(count.intValue());
					orderCounterDetail.setGmtCreate(now);
					orderCounterDetail.setValid(1);
					boolean existsFlag = false;
					for (OrderCounterDetail existsDtl : cDtlList) {
						if(existsDtl.getObjParentId().longValue() == orderCounterDetail.getObjParentId().longValue()){
							existsDtl.setObjParentCount(existsDtl.getObjParentCount() + count.intValue());
							existsFlag = true;
							break;
						}
					}
					if(!existsFlag){
						cDtlList.add(orderCounterDetail);
					}
				}
			}
			for (OrderCounterDetail orderCounterDetail : cDtlList) {
				orderCounterDetailDAO.insert(orderCounterDetail);
			}
		}
		//供应商订单总表
		Map<Long,Object> supplierIdMap = Maps.newHashMap();
		for (int i = 0; i < orderDtlArr.length; i++) {
			//counterId+"_"+objParentId+"_"+supplier.id+"_"+count;
			long supplierId = orderDtlArr[i][2];
			if(!supplierIdMap.containsKey(supplierId)){
				supplierIdMap.put(supplierId, null);
			}
		}
		for(Map.Entry<Long,Object> entry : supplierIdMap.entrySet()){
			OrderSupplier orderSupplier = new OrderSupplier();
			orderSupplier.setOrgSupplierId(entry.getKey());
			orderSupplier.setOrderId(order.getId());
			orderSupplier.setGmtCreate(now);
			orderSupplier.setValid(1);
			orderSupplierDAO.insert(orderSupplier);
			String orderSupplierNo = "S"+f.format(now)+(order.getId() % 10000 + 10000);
			orderSupplier.setOrderSupplierNo(orderSupplierNo);
			orderSupplierDAO.updateByPrimaryKeySelective(orderSupplier);
			//柜台订单明细
			List<OrderSupplierDetail> sDtlList = Lists.newArrayList();
			for (int i = 0; i < orderDtlArr.length; i++) {
				long counterId = orderDtlArr[i][0];
				long objParentId = orderDtlArr[i][1];
				long supplierId = orderDtlArr[i][2];
				Long count = orderDtlArr[i][3];
				if(entry.getKey() == supplierId){
					OrderSupplierDetail orderSupplierDetail = new OrderSupplierDetail();
					orderSupplierDetail.setOrderId(order.getId());
					orderSupplierDetail.setOrderSupplierId(orderSupplier.getId());
					orderSupplierDetail.setObjParentId(objParentId);
					orderSupplierDetail.setObjParentCount(count.intValue());
					orderSupplierDetail.setGmtCreate(now);
					orderSupplierDetail.setValid(1); 
					boolean existsFlag = false;
					for (OrderSupplierDetail existsDtl : sDtlList) {
						if(existsDtl.getObjParentId().longValue() == orderSupplierDetail.getObjParentId().longValue()){
							existsDtl.setObjParentCount(existsDtl.getObjParentCount() + count.intValue());
							existsFlag = true;
							break;
						}
					}
					if(!existsFlag){
						sDtlList.add(orderSupplierDetail);
					}
				}
			}
			for (OrderSupplierDetail orderSupplierDetail : sDtlList) {
				orderSupplierDetailDAO.insert(orderSupplierDetail);
			}
//					orderSupplierDetailDAO.insert(orderSupplierDetail);
//				}
//			}
		}
	}


	public List<Map<String, String>> listErectorTask(String keyword) {
		List<Map<String,String>> result = new ArrayList<>();
		OrderSetup orderSetup = new OrderSetup();
		orderSetup.setValid(1);

		List<OrderSetup> list = this.orderSetupDAO.selectByModel(orderSetup);

		User user = new User();
		user.setValid(1);

		List<User> userList = this.userDAO.selectByModel(user);
		Map<Long, String> userMap = new HashMap<>();

		for(User o : userList) {
			userMap.put(o.getId(),o.getName());
		}


		Organization organization = new Organization();
		organization.setValid(1);
		organization.setType("2");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
		List<Organization> counterList = this.organizationDAO.selectByModel(organization);
		Map<Long, String> counterMap = new HashMap<>();
		for(Organization o : counterList){
			counterMap.put(o.getId(),o.getName());
		}


		for (OrderSetup u : list) {
			Map<String,String> map = new HashMap<>();
			Long userId = u.getUserSetupId() == null ? 0 : u.getUserSetupId();
			String userName = userMap.containsKey(userId) ? userMap.get(userId) : "";
			String counterName = counterMap.containsKey(u.getOrgCounterId()) ? counterMap.get(u.getOrgCounterId()) : "";
			if(keyword == null || keyword.equals("") || u.getId().toString().contains(keyword) || userName.contains(keyword)) {
				map.put("id", u.getId().toString());
				map.put("counterName", counterName);
				map.put("setupUser",userName);
				map.put("setupTime", DateUtil.toDateString(u.getSetupTime()));
				map.put("comment", u.getComment());
				map.put("gmtCreate", DateUtil.toDateString(u.getGmtCreate()));
				result.add(map);
			}
		}
		return result;
	}

	public OrderSetup getOrderSetup(Long id) {
		return orderSetupDAO.selectByPrimaryKey(id);
	}

	public List<OrderCounter> listCounterOrder(String keyword) {
		OrderCounter o = new OrderCounter();
		o.setValid(1);
		return orderCounterDAO.selectByModel(o);
	}

	public void saveOrderSetup(Long id, Long counterId, int taskType, String orderCounterIds,
							   Long userSetupId, Date setupTime, String comment) {
		OrderSetup orderSetup = new OrderSetup();
		if(id > 0) {
			orderSetup = orderSetupDAO.selectByPrimaryKey(id);
		}
		orderSetup.setOrgCounterId(counterId);
		orderSetup.setTaskType(taskType);
		orderSetup.setOrderCounterIds(orderCounterIds);
		orderSetup.setUserSetupId(userSetupId);
		orderSetup.setSetupTime(setupTime);
		orderSetup.setComment(comment);

		orderSetup.setValid(1);
		orderSetup.setGmtModify(new Date());
		if(id>0) {
			//orderSetupDAO.updateByPrimaryKeySelective(orderSetup);
		} else {
			orderSetup.setGmtCreate(new Date());
			orderSetupDAO.insert(orderSetup);
		}
	}
}
