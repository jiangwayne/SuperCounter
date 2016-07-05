package com.plus.server.dal;

import com.plus.server.model.Damage;
import java.util.List;

public interface DamageDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Damage record);

    int insertSelective(Damage record);

    Damage selectByPrimaryKey(Long id);

    List<Damage> selectByModelLike(Damage record);

    List<Damage> selectByModel(Damage record);

    int updateByPrimaryKeySelective(Damage record);

    int updateByPrimaryKey(Damage record);
}