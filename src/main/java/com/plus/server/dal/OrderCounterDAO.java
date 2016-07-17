package com.plus.server.dal;

import com.plus.server.model.OrderCounter;
import java.util.List;

public interface OrderCounterDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OrderCounter record);

    int insertSelective(OrderCounter record);

    OrderCounter selectByPrimaryKey(Long id);

    List<OrderCounter> selectByModelLike(OrderCounter record);

    List<OrderCounter> selectByModel(OrderCounter record);

    int updateByPrimaryKeySelective(OrderCounter record);

    int updateByPrimaryKey(OrderCounter record);
}