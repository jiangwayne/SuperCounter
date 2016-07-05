package com.plus.server.dal;

import com.plus.server.model.Replenish;
import java.util.List;

public interface ReplenishDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Replenish record);

    int insertSelective(Replenish record);

    Replenish selectByPrimaryKey(Long id);

    List<Replenish> selectByModelLike(Replenish record);

    List<Replenish> selectByModel(Replenish record);

    int updateByPrimaryKeySelective(Replenish record);

    int updateByPrimaryKey(Replenish record);
}