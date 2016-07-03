package com.plus.server.dal;

import com.plus.server.model.Furniture;

public interface FurnitureDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Furniture record);

    int insertSelective(Furniture record);

    Furniture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Furniture record);

    int updateByPrimaryKey(Furniture record);
}