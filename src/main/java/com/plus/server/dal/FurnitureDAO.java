package com.plus.server.dal;

import com.plus.server.model.Furniture;
import java.util.List;

public interface FurnitureDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Furniture record);

    int insertSelective(Furniture record);

    Furniture selectByPrimaryKey(Long id);

    List<Furniture> selectByModel(Furniture record);

    int updateByPrimaryKeySelective(Furniture record);

    int updateByPrimaryKey(Furniture record);
}