package com.plus.server.dal;

import com.plus.server.model.Damage;

public interface DamageDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Damage record);

    int insertSelective(Damage record);

    Damage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Damage record);

    int updateByPrimaryKey(Damage record);
}