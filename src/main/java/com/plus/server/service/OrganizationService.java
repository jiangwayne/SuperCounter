package com.plus.server.service;

import com.plus.server.common.util.DateUtil;
import com.plus.server.dal.CounterStyleDAO;
import com.plus.server.dal.FurnitureDAO;
import com.plus.server.dal.OrganizationDAO;
import com.plus.server.model.CounterStyle;
import com.plus.server.model.Furniture;
import com.plus.server.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        organization.setType("品牌");
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

        Map<Long,Organization> brandMap = Support.getInstance().getOrganizationMap("品牌","");

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


    public void saveCounter(Long id, Long orgId, String mediaType, Long styleId, String name,
                            String address, String longLat, String phone, String counterNo, String comment) {
        Organization organization = new Organization();
        if(id > 0) {
            organization = organizationDAO.selectByPrimaryKey(id);
        }
        organization.setParentId(orgId);
        organization.setName(name);
        organization.setType("柜台");
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
    }

    public List<Map<String,String>> getCounterList(String keyword) {
        return Support.getInstance().getCounterList(keyword);
    }

    public List<Map<String,String>> getFurnitureList(String keyword) {
        List<Map<String,String>> result = new ArrayList<>();
        Furniture furniture = new Furniture();
        furniture.setValid(1);

        List<Furniture> list = this.furnitureDAO.selectByModel(furniture);
        Map<Long,Organization> brandMap = Support.getInstance().getOrganizationMap("品牌","");
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
        return Support.getInstance().getOrganizationList("供应商",keyword);
    }

    public void saveSupplier(Long id, String brandIds, String name, String address,
                             String longLat, String phone, String email, String comment) {
        Organization organization = new Organization();
        if(id > 0) {
            organization = organizationDAO.selectByPrimaryKey(id);
        }
        organization.setBrandIds(brandIds);
        organization.setName(name);
        organization.setType("供应商");
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
}
