package com.plus.server.model;

import java.util.Date;

public class OrderSetup {
    private Long id;

    private Long orderId;

    private Long damageId;

    private String orderCounterIds;

    private Long orgCounterId;

    private Long userSetupId;

    private Date setupTime;

    private Integer taskType;

    private String comment;

    private String picUrl;

    private Integer valid;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDamageId() {
        return damageId;
    }

    public void setDamageId(Long damageId) {
        this.damageId = damageId;
    }

    public String getOrderCounterIds() {
        return orderCounterIds;
    }

    public void setOrderCounterIds(String orderCounterIds) {
        this.orderCounterIds = orderCounterIds == null ? null : orderCounterIds.trim();
    }

    public Long getOrgCounterId() {
        return orgCounterId;
    }

    public void setOrgCounterId(Long orgCounterId) {
        this.orgCounterId = orgCounterId;
    }

    public Long getUserSetupId() {
        return userSetupId;
    }

    public void setUserSetupId(Long userSetupId) {
        this.userSetupId = userSetupId;
    }

    public Date getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(Date setupTime) {
        this.setupTime = setupTime;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
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