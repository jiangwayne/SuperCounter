package com.plus.server.dal;

import com.plus.server.model.CounterStyle;

public interface CounterStyleDAO {
    int deleteByPrimaryKey(Long id);

    int insert(CounterStyle record);

    int insertSelective(CounterStyle record);

    CounterStyle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CounterStyle record);

    int updateByPrimaryKey(CounterStyle record);
}