package com.plus.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plus.server.common.util.DateUtil;
import com.plus.server.dal.CounterDetailsDAO;
import com.plus.server.dal.CounterStyleDAO;
import com.plus.server.dal.CounterTemplateDAO;
import com.plus.server.dal.FurnitureDAO;
import com.plus.server.dal.ObjectParentDAO;
import com.plus.server.dal.OrganizationDAO;
import com.plus.server.model.CounterDetails;
import com.plus.server.model.CounterStyle;
import com.plus.server.model.CounterTemplate;
import com.plus.server.model.Furniture;
import com.plus.server.model.ObjectParent;
import com.plus.server.model.Organization;

/**
 * Created by jiangwulin on 16/7/9.
 */
@Service
public class OrganizationService {
    private static final Logger log = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationDAO organizationDAO;

    @Autowired
    private CounterStyleDAO counterStyleDAO;

    @Autowired
    private FurnitureDAO furnitureDAO;

    @Autowired
    private CounterTemplateDAO counterTemplateDAO;

    @Autowired
    private CounterDetailsDAO counterDetailsDAO;
    @Autowired
    private ObjectParentDAO objectParentDAO;

    public List<Map<String,String>> getBrandList(String keyword) {
        return Support.getInstance().getBrandList(keyword);
    }



//    public Map<Long,CounterStyle> getCounterStyleMap(String keyword){
//        return Support.getInstance().getCounterStyleMap(keyword);
//    }

    public Organization getOrg(Long id) {
        return organizationDAO.selectByPrimaryKey(id);
    }

    public void saveBrand(Long id, String name, String phone, String wx, String email,  String comment){
        Organization organization = new Organization();
        if(id > 0) {
            organization = organizationDAO.selectByPrimaryKey(id);
        }
        organization.setParentId(0L);
        organization.setName(name);
        organization.setType("1");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
        organization.setPhone(phone);
        organization.setWxUniqueCode(wx);
        organization.setEmail(email);
        organization.setComment(comment);

        organization.setValid(1);
        organization.setGmtModify(new Date());
        if(id>0) {
            organizationDAO.updateByPrimaryKeySelective(organization);
        } else {
            organization.setGmtCreate(new Date());
            organizationDAO.insert(organization);
        }
    }

    public CounterStyle getCounterStyle(Long id){
        return counterStyleDAO.selectByPrimaryKey(id);
    }

    public void saveCounterStyle(Long id, Long orgId, String name,  String comment){
        CounterStyle counterStyle = new CounterStyle();
        if(id > 0) {
            counterStyle = counterStyleDAO.selectByPrimaryKey(id);
        }
        counterStyle.setName(name);
        counterStyle.setOrgId(orgId);
        counterStyle.setDescription(comment);

        counterStyle.setValid(1);
        counterStyle.setGmtModify(new Date());
        if(id>0) {
            counterStyleDAO.updateByPrimaryKeySelective(counterStyle);
        } else {
            counterStyle.setGmtCreate(new Date());
            counterStyleDAO.insert(counterStyle);
        }
    }


    public List<Map<String,String>> getCounterStyleList(String keyword) {
        List<Map<String,String>> result = new ArrayList<>();
        CounterStyle counterStyle = new CounterStyle();
        counterStyle.setValid(1);
        List<CounterStyle> list = this.counterStyleDAO.selectByModel(counterStyle);

        Map<Long,Organization> brandMap = Support.getInstance().getOrganizationMap("1","");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)

        for (CounterStyle o : list) {
            Map<String,String> map = new HashMap<>();
            if (keyword == null || o.getId().toString().contains(keyword) || o.getName().contains(keyword)) {
                Long orgId = o.getOrgId() == null ? 0 : o.getOrgId();
                map.put("id", o.getId().toString());
                map.put("name", o.getName());
                map.put("orgId", orgId.toString());
                map.put("orgName",brandMap.containsKey(orgId) ? brandMap.get(orgId).getName() : "");
                map.put("comment",o.getDescription());
                map.put("gmtCreate", DateUtil.toDateString(o.getGmtCreate()));
                result.add(map);
            }
        }
        return result;
    }

	@Transactional(rollbackFor = Exception.class)
    public void saveCounter(Long id, Long orgId, String mediaType, Long styleId, String name,
                            String address, String longLat, String phone, String counterNo, String comment) {
        Organization organization = new Organization();
        if(id > 0) {
            organization = organizationDAO.selectByPrimaryKey(id);
        }
        Long oldStyleId = organization.getStyleId();
        organization.setParentId(orgId);
        organization.setName(name);
        organization.setType("2");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
        organization.setPhone(phone);
        organization.setAddress(address);
        organization.setLongLat(longLat);
        organization.setMediaType(mediaType);
        organization.setStyleId(styleId);
        organization.setCounterNo(counterNo);
        organization.setComment(comment);

        organization.setValid(1);
        organization.setGmtModify(new Date());
        if(id>0) {
            organizationDAO.updateByPrimaryKeySelective(organization);
        } else {
            organization.setGmtCreate(new Date());
            organizationDAO.insert(organization);
        }
        oldStyleId = oldStyleId == null?-1L:oldStyleId;
        if(styleId != null && styleId.longValue() != oldStyleId){
        	CounterDetails param = new CounterDetails();
    		param.setOrgId(organization.getId());
    		param.setValid(1);
    		List<CounterDetails> existDtlList = counterDetailsDAO.selectByModel(param);
    		if(existDtlList != null && existDtlList.size() > 0)
    		for(CounterDetails dtl : existDtlList){
    			CounterDetails delParam = new CounterDetails();
    			delParam.setId(dtl.getId());
    			delParam.setValid(-1);
    			counterDetailsDAO.updateByPrimaryKeySelective(delParam);
    		}
    		
        	CounterTemplate ctParam = new CounterTemplate();
        	ctParam.setCounterStyleId(styleId);
        	ctParam.setValid(1);
        	List<CounterTemplate> ctList = counterTemplateDAO.selectByModel(ctParam);
        	if(ctList != null && ctList.size() > 0){
        		for (int i = 0; i < ctList.size(); i++) {
        			CounterTemplate ct = ctList.get(i);
            		CounterDetails cd = new CounterDetails();
            		cd.setOrgId(organization.getId());
            		cd.setObjParentId(ct.getObjParentId());
            		cd.setFurnitureId(ct.getFurnitureId());
            		cd.setCount(ct.getCount() == null? 1 : ct.getCount());
            		cd.setValid(1);
            		cd.setGmtCreate(new Date());
            		counterDetailsDAO.insert(cd);
				}
        		
        	}
        }
    }
	public List<ObjectParent> loadObjParentByFurId(Long furId) {
		// TODO Auto-generated method stub
		return objectParentDAO.loadObjParentByFurId(furId);
	}
	public List<CounterDetails> getCounterDtl(Long counterId) {
		CounterDetails param = new CounterDetails();
		param.setOrgId(counterId);
		param.setValid(1);
		return  counterDetailsDAO.selectByModel(param);
	}
	public void addOrUpdateCounterDtl(CounterDetails d) {
		if(d.getId()!=null)
			counterDetailsDAO.updateByPrimaryKeySelective(d);
		else
			counterDetailsDAO.insert(d);
	}

    public List<Map<String,String>> getCounterList(String keyword) {
        return Support.getInstance().getCounterList(keyword);
    }

    public List<Map<String,String>> getFurnitureList(String keyword) {
        List<Map<String,String>> result = new ArrayList<>();
        Furniture furniture = new Furniture();
        furniture.setValid(1);

        List<Furniture> list = this.furnitureDAO.selectByModel(furniture);
        Map<Long,Organization> brandMap = Support.getInstance().getOrganizationMap("1","");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
        for(Furniture o : list){
            Map<String,String> map = new HashMap<>();
            if(keyword == null || o.getId().toString().contains(keyword) || o.getName().contains(keyword)
                    || o.getFurnitureNo().contains(keyword)){
                Long orgId = o.getOrgId() == null ? 0 : o.getOrgId();
                map.put("id",o.getId().toString());
                map.put("name", o.getName());
                map.put("orgName",brandMap.containsKey(orgId) ? brandMap.get(orgId).getName() : "");
                map.put("comment",o.getComment());
                map.put("gmtCreate", DateUtil.toDateString(o.getGmtCreate()));
                result.add(map);
            }
        }
        return result;
    }


    public List<Furniture> getFurnitureList2(Long brandId,String keyword) {
        List<Furniture> result = new ArrayList<>();
        Furniture furniture = new Furniture();
        furniture.setValid(1);
        if(brandId > 0) {
            furniture.setOrgId(brandId);
        }
        List<Furniture> list = this.furnitureDAO.selectByModel(furniture);
        for(Furniture o : list){
            if(keyword == null || o.getId().toString().contains(keyword) || o.getName().contains(keyword)
                    || o.getFurnitureNo().contains(keyword)){
                result.add(o);
            }
        }
        return result;
    }

    public Map<Long, Furniture> getFurnitureMap2(Long brandId,String keyword) {
        Map<Long, Furniture> result = new HashMap<>();
        Furniture furniture = new Furniture();
        furniture.setValid(1);
        if(brandId > 0) {
            furniture.setOrgId(brandId);
        }
        List<Furniture> list = this.furnitureDAO.selectByModel(furniture);
        for(Furniture o : list){
            if(keyword == null || keyword.equals("") || o.getId().toString().contains(keyword) || o.getName().contains(keyword)
                    || o.getFurnitureNo().contains(keyword)){
                result.put(o.getId(),o);
            }
        }
        return result;
    }

//    public Map<Long, ObjectParent> getOjbParentMap(Long brandId, String keyword) {
//        Map<Long, ObjectParent> result = new HashMap<>();
//        ObjectParent objectParent = new ObjectParent();
//        objectParent.setValid(1);
//        if(brandId > 0) {
//            objectParent.setBrandId(brandId);
//        }
//        List<ObjectParent> list = this.objectParentDAO.selectByModel(objectParent);
//        for(ObjectParent o : list){
//            if(keyword == null || o.getId().toString().contains(keyword) || o.getName().contains(keyword)
//                    || o.get().contains(keyword)){
//                result.put(o.getId(),o);
//            }
//        }
//        return result;
//    }

    public void saveFurniture(Long id, Long orgId, String furnitureNo, String name, String comment) {
        Furniture furniture = new Furniture();
        if(id > 0) {
            furniture = furnitureDAO.selectByPrimaryKey(id);
        }
        furniture.setName(name);
        furniture.setFurnitureNo(furnitureNo);
        furniture.setOrgId(orgId);
        furniture.setComment(comment);

        furniture.setValid(1);
        furniture.setGmtModify(new Date());
        if(id>0) {
            furnitureDAO.updateByPrimaryKeySelective(furniture);
        } else {
            furniture.setGmtCreate(new Date());
            furnitureDAO.insert(furniture);
        }
    }

    public Furniture getFurniture(Long id) {
        return furnitureDAO.selectByPrimaryKey(id);
    }

    public List<Map<String,String>> getSupplierList(String keyword){
        return Support.getInstance().getOrganizationList("3",keyword);//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
    }

    public void saveSupplier(Long id, String brandIds, String name, String address,
                             String longLat, String phone, String email, String comment) {
        Organization organization = new Organization();
        if(id > 0) {
            organization = organizationDAO.selectByPrimaryKey(id);
        }
        organization.setBrandIds(brandIds);
        organization.setName(name);
        organization.setType("3");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
        organization.setPhone(phone);
        organization.setEmail(email);
        organization.setAddress(address);
        organization.setLongLat(longLat);
        organization.setComment(comment);

        organization.setValid(1);
        organization.setGmtModify(new Date());
        if(id>0) {
            organizationDAO.updateByPrimaryKeySelective(organization);
        } else {
            organization.setGmtCreate(new Date());
            organizationDAO.insert(organization);
        }
    }

    public void addCounterTemplate(Long counterStyleId, Long furnitureId, Long objParentId, int count) {
        if(getCounterTemplateList(counterStyleId,furnitureId,objParentId).size() > 0) {
            List<CounterTemplate> list = getCounterTemplateList(counterStyleId,furnitureId,objParentId);
            CounterTemplate counterTemplate = list.get(0);
            counterTemplate.setCount(counterTemplate.getCount()+1);
            counterTemplateDAO.updateByPrimaryKeySelective(counterTemplate);
            return;
        }
        CounterTemplate counterTemplate = new CounterTemplate();
        counterTemplate.setCounterStyleId(counterStyleId);
        counterTemplate.setFurnitureId(furnitureId);
        counterTemplate.setValid(1);
        counterTemplate.setObjParentId(objParentId);
        counterTemplate.setGmtCreate(new Date());
        counterTemplate.setGmtModify(new Date());
        counterTemplate.setCount(count);

        counterTemplateDAO.insertSelective(counterTemplate);
        //return  counterTemplate;
    }

    public List<CounterTemplate> getCounterTemplateList(Long counterStyleId, Long furnitureId, Long objParentId){
        CounterTemplate counterTemplate = new CounterTemplate();
        if(counterStyleId > 0){
            counterTemplate.setCounterStyleId(counterStyleId);
        }
        if(furnitureId > 0){
            counterTemplate.setFurnitureId(furnitureId);
        }
        if(objParentId > 0){
            counterTemplate.setObjParentId(objParentId);
        }

        counterTemplate.setValid(1);
        List<CounterTemplate> list = this.counterTemplateDAO.selectByModel(counterTemplate);
        return list;
    }

    public List<Map<String,String>> getCounterFurnitureMap(Long counterStyleId) {
        List<Map<String,String>> result = new ArrayList<>();
        List<CounterTemplate> list = getCounterTemplateList(counterStyleId,0l, 0l);
        Map<Long,Furniture> furnitureMap = getFurnitureMap2(0L, null);

        for(CounterTemplate o : list){
            Map<String,String> map = new HashMap<>();
            if(o.getObjParentId() != null && o.getObjParentId() > 0){
                continue;
            }
            Long furId = o.getFurnitureId() == null ? 0 : o.getFurnitureId();
            map.put("id",o.getId().toString());
            map.put("counterStyleId", o.getCounterStyleId().toString());
            map.put("furnitureNo", furnitureMap.containsKey(furId) ? furnitureMap.get(furId).getFurnitureNo() : "");
            map.put("furnitureName", furnitureMap.containsKey(furId) ? furnitureMap.get(furId).getName() : "");
            map.put("gmtCreate", DateUtil.toDateString(o.getGmtCreate()));
            result.add(map);
        }
        return result;
    }

    public void deleteCounterTemplate(Long id) {
        CounterTemplate counterTemplate = new CounterTemplate();
        counterTemplate.setId(id);
        counterTemplate.setValid(-1);

        counterTemplateDAO.updateByPrimaryKeySelective(counterTemplate);
    }

    public List<ObjectParent> getObjParent(Long brandId, String keyword) {
        List<ObjectParent> result = new ArrayList<>();
        ObjectParent objectParent = new ObjectParent();
        objectParent.setValid(1);
        if(brandId > 0) {
            objectParent.setBrandId(brandId);
        }
        List<ObjectParent> list = this.objectParentDAO.selectByModel(objectParent);
        for(ObjectParent o : list){
            if(keyword == null || keyword.equals("") || o.getId().toString().contains(keyword) || o.getName().contains(keyword)
                    || o.getObjParentNo().contains(keyword)){
                result.add(o);
            }
        }
        return result;
    }

    public Map<Long,ObjectParent> getObjParentMap(Long brandId, String keyword) {
        Map<Long,ObjectParent> result = new HashMap<>();
        ObjectParent objectParent = new ObjectParent();
        objectParent.setValid(1);
        if(brandId > 0) {
            objectParent.setBrandId(brandId);
        }
        List<ObjectParent> list = this.objectParentDAO.selectByModel(objectParent);
        for(ObjectParent o : list){
            if(keyword == null || keyword.equals("") || o.getId().toString().contains(keyword) || o.getName().contains(keyword)
                    || o.getObjParentNo().contains(keyword)){
                result.put(o.getId(), o);
            }
        }
        return result;
    }


    public List<Map<String,String>> getFurnitureObjParentMap(Long furnitureId) {
        List<Map<String,String>> result = new ArrayList<>();
        List<CounterTemplate> list = getCounterTemplateList(0l, furnitureId, 0l);

        Map<Long,ObjectParent> objParentMap = getObjParentMap(0L, null);

        for(CounterTemplate o : list){
            Map<String,String> map = new HashMap<>();
            if(o.getCounterStyleId() != null && o.getCounterStyleId() > 0){
                continue;
            }
            Long objParentId = o.getObjParentId() == null ? 0 : o.getObjParentId();
            map.put("id",o.getId().toString());
            map.put("objParentNo", objParentMap.containsKey(objParentId) ? objParentMap.get(objParentId).getObjParentNo() : "");
            map.put("objParentName", objParentMap.containsKey(objParentId) ? objParentMap.get(objParentId).getName() : "");
            map.put("objParentCount", objParentMap.containsKey(objParentId) ? o.getCount()+"" : "");
            map.put("gmtCreate", DateUtil.toDateString(o.getGmtCreate()));
            result.add(map);
        }
        return result;
    }
}
