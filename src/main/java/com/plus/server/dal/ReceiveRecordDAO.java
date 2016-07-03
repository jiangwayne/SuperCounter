package com.plus.server.dal;

import com.plus.server.model.ReceiveRecord;
import java.util.List;

public interface ReceiveRecordDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ReceiveRecord record);

    int insertSelective(ReceiveRecord record);

    ReceiveRecord selectByPrimaryKey(Long id);

    List<ReceiveRecord> selectByModel(ReceiveRecord record);

    int updateByPrimaryKeySelective(ReceiveRecord record);

    int updateByPrimaryKey(ReceiveRecord record);
}