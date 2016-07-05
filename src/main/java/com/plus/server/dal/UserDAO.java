package com.plus.server.dal;

import com.plus.server.model.User;
import java.util.List;

public interface UserDAO {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectByModelLike(User record);

    List<User> selectByModel(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}