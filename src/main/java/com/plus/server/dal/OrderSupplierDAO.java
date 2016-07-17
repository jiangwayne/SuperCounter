package com.plus.server.dal;

import com.plus.server.model.OrderSupplier;
import java.util.List;

public interface OrderSupplierDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OrderSupplier record);

    int insertSelective(OrderSupplier record);

    OrderSupplier selectByPrimaryKey(Long id);

    List<OrderSupplier> selectByModelLike(OrderSupplier record);

    List<OrderSupplier> selectByModel(OrderSupplier record);

    int updateByPrimaryKeySelective(OrderSupplier record);

    int updateByPrimaryKey(OrderSupplier record);
}