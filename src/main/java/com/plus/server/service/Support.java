package com.plus.server.service;

import com.plus.server.dal.OrganizationDAO;
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

    private static Support support = new Support();

    private Support(){
        if(organizationDAO == null) {
            organizationDAO = ContextLoader.getCurrentWebApplicationContext().getBean(OrganizationDAO.class);
        }
    }

    public static Support getInstance(){
        return support;
    }

    public Map<Long,Organization> getOrganizationMap(String Type, String keyword) {
        Map<Long,Organization> result = new HashMap<>();
        Organization organization = new Organization();
        organization.setValid(1);
        if(!Type.equals("")) {
            organization.setType(Type);
        }
        List<Organization> list = this.organizationDAO.selectByModel(organization);
        for (Organization o : list) {
            if(keyword == null || o.getId().toString().contains(keyword) || o.getName().contains(keyword)) {
                result.put(o.getId(),o);
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
}
