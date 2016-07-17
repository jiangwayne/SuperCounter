package com.plus.server.common.vo.resp;

import java.util.List;

import com.plus.server.model.ObjectParent;

public class LoadObjParentByFurIdResp extends BaseResp{
	private List<ObjectParent> objParentList;

	public List<ObjectParent> getObjParentList() {
		return objParentList;
	}

	public void setObjParentList(List<ObjectParent> objParentList) {
		this.objParentList = objParentList;
	}

	
}
