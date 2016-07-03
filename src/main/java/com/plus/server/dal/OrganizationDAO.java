package com.plus.server.dal;

import com.plus.server.model.Organization;
import java.util.List;

public interface OrganizationDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Long id);

    List<Organization> selectByModel(Organization record);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
}