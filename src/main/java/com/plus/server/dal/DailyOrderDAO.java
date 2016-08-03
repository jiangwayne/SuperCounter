package com.plus.server.dal;

import com.plus.server.model.DailyOrder;
import java.util.List;

public interface DailyOrderDAO {
    int deleteByPrimaryKey(Long id);

    int insert(DailyOrder record);

    int insertSelective(DailyOrder record);

    DailyOrder selectByPrimaryKey(Long id);

    List<DailyOrder> selectByModelLike(DailyOrder record);

    List<DailyOrder> selectByModel(DailyOrder record);

    int updateByPrimaryKeySelective(DailyOrder record);

    int updateByPrimaryKey(DailyOrder record);
}