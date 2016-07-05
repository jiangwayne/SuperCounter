package com.plus.server.dal;

import com.plus.server.model.CounterTemplate;
import java.util.List;

public interface CounterTemplateDAO {
    int deleteByPrimaryKey(Long id);

    int insert(CounterTemplate record);

    int insertSelective(CounterTemplate record);

    CounterTemplate selectByPrimaryKey(Long id);

    List<CounterTemplate> selectByModelLike(CounterTemplate record);

    List<CounterTemplate> selectByModel(CounterTemplate record);

    int updateByPrimaryKeySelective(CounterTemplate record);

    int updateByPrimaryKey(CounterTemplate record);
}