package com.plus.server.dal;

import com.plus.server.model.EverydayInfo;

public interface EverydayInfoDAO {
    int deleteByPrimaryKey(Long id);

    int insert(EverydayInfo record);

    int insertSelective(EverydayInfo record);

    EverydayInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EverydayInfo record);

    int updateByPrimaryKey(EverydayInfo record);
}