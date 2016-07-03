package com.plus.server.dal;

import com.plus.server.model.ParentChild;
import java.util.List;

public interface ParentChildDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ParentChild record);

    int insertSelective(ParentChild record);

    ParentChild selectByPrimaryKey(Long id);

    List<ParentChild> selectByModel(ParentChild record);

    int updateByPrimaryKeySelective(ParentChild record);

    int updateByPrimaryKey(ParentChild record);
}