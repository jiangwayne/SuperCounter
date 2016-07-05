package com.plus.server.dal;

import com.plus.server.model.SetupRecord;
import java.util.List;

public interface SetupRecordDAO {
    int deleteByPrimaryKey(Long id);

    int insert(SetupRecord record);

    int insertSelective(SetupRecord record);

    SetupRecord selectByPrimaryKey(Long id);

    List<SetupRecord> selectByModelLike(SetupRecord record);

    List<SetupRecord> selectByModel(SetupRecord record);

    int updateByPrimaryKeySelective(SetupRecord record);

    int updateByPrimaryKey(SetupRecord record);
}