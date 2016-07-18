package com.plus.server.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plus.server.dal.CounterDetailsDAO;
import com.plus.server.dal.FurnitureDAO;
import com.plus.server.dal.OrderCounterDAO;
import com.plus.server.dal.OrderCounterDetailDAO;
import com.plus.server.dal.OrderDAO;
import com.plus.server.dal.OrderSupplierDAO;
import com.plus.server.dal.OrderSupplierDetailDAO;
import com.plus.server.dal.OrganizationDAO;
import com.plus.server.model.CounterDetails;
import com.plus.server.model.Furniture;
import com.plus.server.model.Order;
import com.plus.server.model.OrderCounter;
import com.plus.server.model.OrderCounterDetail;
import com.plus.server.model.OrderSupplier;
import com.plus.server.model.OrderSupplierDetail;
import com.plus.server.model.Organization;

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

//	@Autowired
//	private OrderSetupDao

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


	public List<Map<String, String>> listErectorTask(String keyWord) {

		return null;
	}
}
