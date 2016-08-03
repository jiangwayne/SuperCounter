package com.plus.server.service;

import com.plus.server.common.util.DateUtil;
import com.plus.server.dal.CounterStyleDAO;
import com.plus.server.dal.OrganizationDAO;
import com.plus.server.model.CounterStyle;
import com.plus.server.model.Organization;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangwulin on 16/7/9.
 */
public class Support {
    private OrganizationDAO organizationDAO;
    private CounterStyleDAO counterStyleDAO;

    private static Support support = new Support();

    private Map<Long,Map<String, Integer>> roleAction;

    private Support(){
        if(organizationDAO == null) {
            organizationDAO = ContextLoader.getCurrentWebApplicationContext().getBean(OrganizationDAO.class);
        }
        if(counterStyleDAO == null){
            counterStyleDAO = ContextLoader.getCurrentWebApplicationContext().getBean(CounterStyleDAO.class);
        }
        roleAction = new HashMap<>();


        Map<String, Integer> map6 = new HashMap<>(); //安装工权限
        map6.put("list",1);
        roleAction.put(6l,map6);

        Map<String, Integer> map5 = new HashMap<>(); //BA权限
        map5.put("list",1);
        roleAction.put(5l,map5);

        Map<String, Integer> map4 = new HashMap<>(); //供应商
        map4.put("list",1);
        roleAction.put(4l,map4);
    }

    public static Support getInstance(){
        return support;
    }

    public Map<Long,Organization> getOrganizationMap(String type, String keyword) {
        Map<Long,Organization> result = new HashMap<>();
        Organization organization = new Organization();
        organization.setValid(1);
        if(!type.equals("")) {
            organization.setType(type);
        }
        List<Organization> list = this.organizationDAO.selectByModel(organization);
        for (Organization o : list) {
            if(keyword == null || keyword.equals("") || o.getId().toString().contains(keyword) || o.getName().contains(keyword)) {
                result.put(o.getId(),o);
            }
        }
        return result;
    }

    public List<Map<String,String>> getBrandList(String keyword) {
        List<Map<String,String>> result = new ArrayList<>();
        Organization organization = new Organization();
        organization.setValid(1);
        organization.setType("1");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
        List<Organization> list = this.organizationDAO.selectByModel(organization);
        for (Organization o : list) {
            Map<String,String> map = new HashMap<>();
            if(keyword == null || keyword.equals("") || o.getId().toString().contains(keyword) || o.getName().contains(keyword)) {
                map.put("id", o.getId().toString());
                map.put("name", o.getName());
                map.put("email", o.getEmail());
                map.put("phone",o.getPhone());
                map.put("comment",o.getComment());
                map.put("gmtCreate", DateUtil.toDateString(o.getGmtCreate()));
                result.add(map);
            }
        }
        return result;
    }

    public List<Map<String,String>> getOrganizationList(String type, String keyword) {
        List<Map<String,String>> result = new ArrayList<>();
        Organization organization = new Organization();
        organization.setValid(1);
        if(!type.equals("")) {
            organization.setType(type);
        }
        List<Organization> list = this.organizationDAO.selectByModel(organization);
        for (Organization o : list) {
            Map<String,String> map = new HashMap<>();
            if(keyword == null || keyword.equals("") || o.getId().toString().contains(keyword) || o.getName().contains(keyword)) {
                map.put("id", o.getId().toString());
                map.put("name", o.getName());
                map.put("type", o.getType().toString());
                map.put("address", o.getAddress());
                map.put("phone",o.getPhone());
                map.put("email",o.getEmail());
                map.put("comment",o.getComment());
                map.put("mediaType", o.getMediaType());
                map.put("styleId",o.getStyleId() != null && o.getStyleId() > 0 ? o.getStyleId().toString() : "0");
                map.put("lastLongLat", o.getLongLat());
                map.put("gmtCreate",DateUtil.toDateString(o.getGmtCreate()));
                result.add(map);
            }
        }
        return result;
    }

    public Map<Long,CounterStyle> getCounterStyleMap(String keyword){
        Map<Long,CounterStyle> result = new HashMap<>();
        CounterStyle counterStyle = new CounterStyle();
        counterStyle.setValid(1);

        List<CounterStyle> list = this.counterStyleDAO.selectByModel(counterStyle);
        for(CounterStyle o : list){
            if(keyword == null || o.getId().toString().contains(keyword) || o.getName().contains(keyword)) {
                result.put(o.getId(),o);
            }
        }
        return result;
    }

    public List<Map<String,String>> getCounterList(String keyword) {
        List<Map<String,String>> result = new ArrayList<>();
        Organization organization = new Organization();
        organization.setValid(1);
        organization.setType("2");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
        List<Organization> list = this.organizationDAO.selectByModel(organization);
        Map<Long,Organization> brandMap = getOrganizationMap("1","");//组织类型(1：品牌，2：柜台，3：供应商，4：物流，5：陈列)
        Map<Long,CounterStyle> styleMap = getCounterStyleMap("");
        for (Organization o : list) {
            Map<String,String> map = new HashMap<>();
            if(keyword == null || keyword.equals("") || o.getId().toString().contains(keyword) || o.getName().contains(keyword)) {
                Long parenId = o.getParentId() == null ? 0 : o.getParentId();
                Long styleId = o.getStyleId() == null ? 0 : o.getStyleId();
                map.put("id", o.getId().toString());
                map.put("name", o.getName());
                map.put("orgName",brandMap.containsKey(parenId) ? brandMap.get(parenId).getName() : "");
                map.put("mediaType", o.getMediaType());
                map.put("styleName",styleMap.containsKey(styleId) ? styleMap.get(styleId).getName() : "");
                map.put("comment",o.getComment());
                map.put("gmtCreate", DateUtil.toDateString(o.getGmtCreate()));
                result.add(map);
            }
        }
        return result;
    }

    public <E> List<E> take(List<E> list, int number){
        if(number >= list.size()){
            return list;
        }

        List<E> result = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            result.add(list.get(i));
        }

        return result;
    }

    public <E> List<E> skip(List<E> list, int number){
        if(number >= list.size()){
            list.clear();
            return list;
        }

        List<E> result = new ArrayList<>();
        for (int i = number; i < list.size(); i++) {
            result.add(list.get(i));
        }

        return result;
    }

    public boolean allowed(Long roleId, String action){
        if(roleId == 1 || (roleAction.containsKey(roleId) && roleAction.get(roleId).containsKey(action))){
            return true;
        }
        return false;
    }
}
