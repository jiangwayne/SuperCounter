package com.plus.server.dal;

import com.plus.server.model.CounterDetails;
import java.util.List;

public interface CounterDetailsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(CounterDetails record);

    int insertSelective(CounterDetails record);

    CounterDetails selectByPrimaryKey(Long id);

    List<CounterDetails> selectByModel(CounterDetails record);

    int updateByPrimaryKeySelective(CounterDetails record);

    int updateByPrimaryKey(CounterDetails record);
}