package com.plus.server.dal;

import com.plus.server.model.Express;
import java.util.List;

public interface ExpressDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Express record);

    int insertSelective(Express record);

    Express selectByPrimaryKey(Long id);

    List<Express> selectByModelLike(Express record);

    List<Express> selectByModel(Express record);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);
}