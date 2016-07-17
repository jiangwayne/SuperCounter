package com.plus.server.dal;

import com.plus.server.model.Order;
import java.util.List;

public interface OrderDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    List<Order> selectByModelLike(Order record);

    List<Order> selectByModel(Order record);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}