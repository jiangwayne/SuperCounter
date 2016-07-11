package com.plus.server.model;

import java.util.Date;

public class SetupRecord {
    private Long id;

    private Long orderSetupId;

    private Long userSetupId;

    private Long objParentId;

    private String picUrl;

    private String discription;

    private Integer status;

    private String longLat;

    private Integer valid;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderSetupId() {
        return orderSetupId;
    }

    public void setOrderSetupId(Long orderSetupId) {
        this.orderSetupId = orderSetupId;
    }

    public Long getUserSetupId() {
        return userSetupId;
    }

    public void setUserSetupId(Long userSetupId) {
        this.userSetupId = userSetupId;
    }

    public Long getObjParentId() {
        return objParentId;
    }

    public void setObjParentId(Long objParentId) {
        this.objParentId = objParentId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription == null ? null : discription.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLongLat() {
        return longLat;
    }

    public void setLongLat(String longLat) {
        this.longLat = longLat == null ? null : longLat.trim();
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