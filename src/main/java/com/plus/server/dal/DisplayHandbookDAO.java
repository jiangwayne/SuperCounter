package com.plus.server.dal;

import com.plus.server.model.DisplayHandbook;
import java.util.List;

public interface DisplayHandbookDAO {
    int deleteByPrimaryKey(Long id);

    int insert(DisplayHandbook record);

    int insertSelective(DisplayHandbook record);

    DisplayHandbook selectByPrimaryKey(Long id);

    List<DisplayHandbook> selectByModelLike(DisplayHandbook record);

    List<DisplayHandbook> selectByModel(DisplayHandbook record);

    int updateByPrimaryKeySelective(DisplayHandbook record);

    int updateByPrimaryKey(DisplayHandbook record);
}