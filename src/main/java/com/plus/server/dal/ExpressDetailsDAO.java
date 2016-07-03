package com.plus.server.dal;

import com.plus.server.model.ExpressDetails;

public interface ExpressDetailsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ExpressDetails record);

    int insertSelective(ExpressDetails record);

    ExpressDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExpressDetails record);

    int updateByPrimaryKey(ExpressDetails record);
}