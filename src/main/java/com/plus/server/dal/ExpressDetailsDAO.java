package com.plus.server.dal;

import com.plus.server.model.ExpressDetails;
import java.util.List;

public interface ExpressDetailsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ExpressDetails record);

    int insertSelective(ExpressDetails record);

    ExpressDetails selectByPrimaryKey(Long id);

    List<ExpressDetails> selectByModel(ExpressDetails record);

    int updateByPrimaryKeySelective(ExpressDetails record);

    int updateByPrimaryKey(ExpressDetails record);
}