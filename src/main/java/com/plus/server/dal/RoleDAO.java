package com.plus.server.dal;

import com.plus.server.model.Role;
import java.util.List;

public interface RoleDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectByModelLike(Role record);

    List<Role> selectByModel(Role record);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}