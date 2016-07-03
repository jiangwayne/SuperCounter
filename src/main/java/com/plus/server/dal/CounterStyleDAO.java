package com.plus.server.dal;

import com.plus.server.model.CounterStyle;
import java.util.List;

public interface CounterStyleDAO {
    int deleteByPrimaryKey(Long id);

    int insert(CounterStyle record);

    int insertSelective(CounterStyle record);

    CounterStyle selectByPrimaryKey(Long id);

    List<CounterStyle> selectByModel(CounterStyle record);

    int updateByPrimaryKeySelective(CounterStyle record);

    int updateByPrimaryKey(CounterStyle record);
}