package com.plus.server.dal;

import com.plus.server.model.OjbOrderDetails;

public interface OjbOrderDetailsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OjbOrderDetails record);

    int insertSelective(OjbOrderDetails record);

    OjbOrderDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OjbOrderDetails record);

    int updateByPrimaryKey(OjbOrderDetails record);
}