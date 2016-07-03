package com.plus.server.dal;

import com.plus.server.model.EverydayInfo;
import java.util.List;

public interface EverydayInfoDAO {
    int deleteByPrimaryKey(Long id);

    int insert(EverydayInfo record);

    int insertSelective(EverydayInfo record);

    EverydayInfo selectByPrimaryKey(Long id);

    List<EverydayInfo> selectByModel(EverydayInfo record);

    int updateByPrimaryKeySelective(EverydayInfo record);

    int updateByPrimaryKey(EverydayInfo record);
}