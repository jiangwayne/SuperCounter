package com.plus.server.dal;

import com.plus.server.model.OjbOrder;
import java.util.List;

public interface OjbOrderDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OjbOrder record);

    int insertSelective(OjbOrder record);

    OjbOrder selectByPrimaryKey(Long id);

    List<OjbOrder> selectByModelLike(OjbOrder record);

    List<OjbOrder> selectByModel(OjbOrder record);

    int updateByPrimaryKeySelective(OjbOrder record);

    int updateByPrimaryKey(OjbOrder record);
}