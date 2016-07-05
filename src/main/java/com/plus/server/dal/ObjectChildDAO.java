package com.plus.server.dal;

import com.plus.server.model.ObjectChild;
import java.util.List;

public interface ObjectChildDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ObjectChild record);

    int insertSelective(ObjectChild record);

    ObjectChild selectByPrimaryKey(Long id);

    List<ObjectChild> selectByModelLike(ObjectChild record);

    List<ObjectChild> selectByModel(ObjectChild record);

    int updateByPrimaryKeySelective(ObjectChild record);

    int updateByPrimaryKey(ObjectChild record);
}