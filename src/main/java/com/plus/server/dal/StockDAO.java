package com.plus.server.dal;

import com.plus.server.model.Stock;
import java.util.List;

public interface StockDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Long id);

    List<Stock> selectByModel(Stock record);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}