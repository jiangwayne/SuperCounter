package com.plus.server.dal;

import java.util.List;

import com.plus.server.model.ObjectParent;

public interface ObjectParentDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ObjectParent record);

    int insertSelective(ObjectParent record);

    ObjectParent selectByPrimaryKey(Long id);

    List<ObjectParent> selectByModelLike(ObjectParent record);

    List<ObjectParent> selectByModel(ObjectParent record);

    int updateByPrimaryKeySelective(ObjectParent record);

    int updateByPrimaryKey(ObjectParent record);

	List<ObjectParent> loadObjParentByFurId(Long furId);
}