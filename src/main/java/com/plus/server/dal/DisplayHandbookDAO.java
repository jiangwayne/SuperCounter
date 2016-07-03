package com.plus.server.dal;

import com.plus.server.model.DisplayHandbook;

public interface DisplayHandbookDAO {
    int deleteByPrimaryKey(Long id);

    int insert(DisplayHandbook record);

    int insertSelective(DisplayHandbook record);

    DisplayHandbook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DisplayHandbook record);

    int updateByPrimaryKey(DisplayHandbook record);
}