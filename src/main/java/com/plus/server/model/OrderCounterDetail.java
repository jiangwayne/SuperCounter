package com.plus.server.model;

import java.util.Date;

public class OrderCounterDetail {
    private Long id;

    private Long orderId;

    private Long orderCounterId;

    private Long objParentId;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderCounterId() {
        return orderCounterId;
    }

    public void setOrderCounterId(Long orderCounterId) {
        this.orderCounterId = orderCounterId;
    }

    public Long getObjParentId() {
        return objParentId;
    }

    public void setObjParentId(Long objParentId) {
        this.objParentId = objParentId;
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
}