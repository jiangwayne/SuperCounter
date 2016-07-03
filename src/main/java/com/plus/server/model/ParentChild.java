package com.plus.server.model;

import java.util.Date;

public class ParentChild {
    private Long id;

    private Long objParentId;

    private Long objChildId;

    private Integer objChildCount;

    private Integer valid;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjParentId() {
        return objParentId;
    }

    public void setObjParentId(Long objParentId) {
        this.objParentId = objParentId;
    }

    public Long getObjChildId() {
        return objChildId;
    }

    public void setObjChildId(Long objChildId) {
        this.objChildId = objChildId;
    }

    public Integer getObjChildCount() {
        return objChildCount;
    }

    public void setObjChildCount(Integer objChildCount) {
        this.objChildCount = objChildCount;
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