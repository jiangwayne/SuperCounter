package com.plus.server.dal;

import com.plus.server.model.OrderCounterDetail;
import java.util.List;

public interface OrderCounterDetailDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OrderCounterDetail record);

    int insertSelective(OrderCounterDetail record);

    OrderCounterDetail selectByPrimaryKey(Long id);

    List<OrderCounterDetail> selectByModelLike(OrderCounterDetail record);

    List<OrderCounterDetail> selectByModel(OrderCounterDetail record);

    int updateByPrimaryKeySelective(OrderCounterDetail record);

    int updateByPrimaryKey(OrderCounterDetail record);
}