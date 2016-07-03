package com.plus.server.dal;

import com.plus.server.model.UserRole;
import java.util.List;

public interface UserRoleDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    List<UserRole> selectByModel(UserRole record);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}