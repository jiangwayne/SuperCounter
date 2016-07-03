package com.plus.server.dal;

import com.plus.server.model.SetupRecord;

public interface SetupRecordDAO {
    int deleteByPrimaryKey(Long id);

    int insert(SetupRecord record);

    int insertSelective(SetupRecord record);

    SetupRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SetupRecord record);

    int updateByPrimaryKey(SetupRecord record);
}