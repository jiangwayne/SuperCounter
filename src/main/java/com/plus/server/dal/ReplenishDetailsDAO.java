package com.plus.server.dal;

import com.plus.server.model.ReplenishDetails;

public interface ReplenishDetailsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ReplenishDetails record);

    int insertSelective(ReplenishDetails record);

    ReplenishDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReplenishDetails record);

    int updateByPrimaryKey(ReplenishDetails record);
}