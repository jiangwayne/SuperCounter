package com.plus.server.model;

import java.util.Date;

public class DailyOrder {
    private Long id;

    private String dailyOrderNo;

    private Long orgCounterId;
    private Organization orgCounter;

    private Long objParentId;
    private ObjectParent objParent;

    private Long supplyOrgId;
    private Organization supplyOrg;

    private Integer objParentCount;

    private String comment;

    private Integer valid;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDailyOrderNo() {
        return dailyOrderNo;
    }

    public void setDailyOrderNo(String dailyOrderNo) {
        this.dailyOrderNo = dailyOrderNo == null ? null : dailyOrderNo.trim();
    }

    public Long getOrgCounterId() {
        return orgCounterId;
    }

    public void setOrgCounterId(Long orgCounterId) {
        this.orgCounterId = orgCounterId;
    }

    public Long getObjParentId() {
        return objParentId;
    }

    public void setObjParentId(Long objParentId) {
        this.objParentId = objParentId;
    }

    public Long getSupplyOrgId() {
        return supplyOrgId;
    }

    public void setSupplyOrgId(Long supplyOrgId) {
        this.supplyOrgId = supplyOrgId;
    }

    public Integer getObjParentCount() {
        return objParentCount;
    }

    public void setObjParentCount(Integer objParentCount) {
        this.objParentCount = objParentCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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

	public Organization getOrgCounter() {
		return orgCounter;
	}

	public void setOrgCounter(Organization orgCounter) {
		this.orgCounter = orgCounter;
	}

	public ObjectParent getObjParent() {
		return objParent;
	}

	public void setObjParent(ObjectParent objParent) {
		this.objParent = objParent;
	}

	public Organization getSupplyOrg() {
		return supplyOrg;
	}

	public void setSupplyOrg(Organization supplyOrg) {
		this.supplyOrg = supplyOrg;
	}
}