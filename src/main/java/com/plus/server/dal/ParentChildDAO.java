package com.plus.server.dal;

import com.plus.server.model.ParentChild;

public interface ParentChildDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ParentChild record);

    int insertSelective(ParentChild record);

    ParentChild selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ParentChild record);

    int updateByPrimaryKey(ParentChild record);
}