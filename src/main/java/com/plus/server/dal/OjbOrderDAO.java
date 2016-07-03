package com.plus.server.dal;

import com.plus.server.model.OjbOrder;

public interface OjbOrderDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OjbOrder record);

    int insertSelective(OjbOrder record);

    OjbOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OjbOrder record);

    int updateByPrimaryKey(OjbOrder record);
}