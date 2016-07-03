package com.plus.server.dal;

import com.plus.server.model.Replenish;

public interface ReplenishDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Replenish record);

    int insertSelective(Replenish record);

    Replenish selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Replenish record);

    int updateByPrimaryKey(Replenish record);
}