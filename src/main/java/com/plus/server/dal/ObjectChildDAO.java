package com.plus.server.dal;

import com.plus.server.model.ObjectChild;

public interface ObjectChildDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ObjectChild record);

    int insertSelective(ObjectChild record);

    ObjectChild selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ObjectChild record);

    int updateByPrimaryKey(ObjectChild record);
}