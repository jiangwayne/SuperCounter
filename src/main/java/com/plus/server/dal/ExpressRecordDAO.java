package com.plus.server.dal;

import com.plus.server.model.ExpressRecord;

public interface ExpressRecordDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ExpressRecord record);

    int insertSelective(ExpressRecord record);

    ExpressRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExpressRecord record);

    int updateByPrimaryKey(ExpressRecord record);
}