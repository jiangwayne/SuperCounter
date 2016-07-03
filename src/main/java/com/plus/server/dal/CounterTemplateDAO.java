package com.plus.server.dal;

import com.plus.server.model.CounterTemplate;

public interface CounterTemplateDAO {
    int deleteByPrimaryKey(Long id);

    int insert(CounterTemplate record);

    int insertSelective(CounterTemplate record);

    CounterTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CounterTemplate record);

    int updateByPrimaryKey(CounterTemplate record);
}