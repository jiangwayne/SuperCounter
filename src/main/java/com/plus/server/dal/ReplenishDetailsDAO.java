package com.plus.server.dal;

import com.plus.server.model.ReplenishDetails;
import java.util.List;

public interface ReplenishDetailsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ReplenishDetails record);

    int insertSelective(ReplenishDetails record);

    ReplenishDetails selectByPrimaryKey(Long id);

    List<ReplenishDetails> selectByModel(ReplenishDetails record);

    int updateByPrimaryKeySelective(ReplenishDetails record);

    int updateByPrimaryKey(ReplenishDetails record);
}