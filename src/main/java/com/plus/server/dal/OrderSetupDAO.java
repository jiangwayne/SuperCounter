package com.plus.server.dal;

import com.plus.server.model.OrderSetup;
import java.util.List;

public interface OrderSetupDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OrderSetup record);

    int insertSelective(OrderSetup record);

    OrderSetup selectByPrimaryKey(Long id);

    List<OrderSetup> selectByModelLike(OrderSetup record);

    List<OrderSetup> selectByModel(OrderSetup record);

    int updateByPrimaryKeySelective(OrderSetup record);

    int updateByPrimaryKey(OrderSetup record);
}