package com.plus.server.dal;

import com.plus.server.model.OrderSupplierDetail;
import java.util.List;

public interface OrderSupplierDetailDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OrderSupplierDetail record);

    int insertSelective(OrderSupplierDetail record);

    OrderSupplierDetail selectByPrimaryKey(Long id);

    List<OrderSupplierDetail> selectByModelLike(OrderSupplierDetail record);

    List<OrderSupplierDetail> selectByModel(OrderSupplierDetail record);

    int updateByPrimaryKeySelective(OrderSupplierDetail record);

    int updateByPrimaryKey(OrderSupplierDetail record);
}