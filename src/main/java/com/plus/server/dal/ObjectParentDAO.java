package com.plus.server.dal;

import com.plus.server.model.ObjectParent;

public interface ObjectParentDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ObjectParent record);

    int insertSelective(ObjectParent record);

    ObjectParent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ObjectParent record);

    int updateByPrimaryKey(ObjectParent record);
}