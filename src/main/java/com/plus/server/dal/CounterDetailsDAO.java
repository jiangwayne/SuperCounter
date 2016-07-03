package com.plus.server.dal;

import com.plus.server.model.CounterDetails;

public interface CounterDetailsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(CounterDetails record);

    int insertSelective(CounterDetails record);

    CounterDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CounterDetails record);

    int updateByPrimaryKey(CounterDetails record);
}