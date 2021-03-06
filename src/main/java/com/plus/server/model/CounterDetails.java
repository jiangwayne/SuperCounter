package com.plus.server.model;

import java.util.Date;

public class CounterDetails {
    private Long id;

    private Long orgId;

    public Organization getCounter() {
        return counter;
    }

    public void setCounter(Organization counter) {
        this.counter = counter;
    }

    private Organization counter;

    private Long objParentId;
    private ObjectParent objectParent;
    
    private Long furnitureId;
    private Furniture furniture;

    private Integer count;

    private String siteNo;

    private Integer valid;

    private Date gmtCreate;

    private Date gmtModify;

    public ObjectParent getObjectParent() {
		return objectParent;
	}

	public void setObjectParent(ObjectParent objectParent) {
		this.objectParent = objectParent;
	}

	public Furniture getFurniture() {
		return furniture;
	}

	public void setFurniture(Furniture furniture) {
		this.furniture = furniture;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getObjParentId() {
        return objParentId;
    }

    public void setObjParentId(Long objParentId) {
        this.objParentId = objParentId;
    }

    public Long getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo == null ? null : siteNo.trim();
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}