package com.plus.server.dal;

import com.plus.server.model.ExpressRecord;
import java.util.List;

public interface ExpressRecordDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ExpressRecord record);

    int insertSelective(ExpressRecord record);

    ExpressRecord selectByPrimaryKey(Long id);

    List<ExpressRecord> selectByModel(ExpressRecord record);

    int updateByPrimaryKeySelective(ExpressRecord record);

    int updateByPrimaryKey(ExpressRecord record);
}