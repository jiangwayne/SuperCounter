package com.plus.server.dal;

import com.plus.server.model.ReceiveRecord;

public interface ReceiveRecordDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ReceiveRecord record);

    int insertSelective(ReceiveRecord record);

    ReceiveRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceiveRecord record);

    int updateByPrimaryKey(ReceiveRecord record);
}