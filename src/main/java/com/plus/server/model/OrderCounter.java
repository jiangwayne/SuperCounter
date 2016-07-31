package com.plus.server.model;

import java.util.Date;


public class OrderCounter {
    private Long id;

    private Long orderId;

    private Long orgCounterId;

    private Organization orgCounter;

    private String orderCounterNo;

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

    public Long getOrgCounterId() {
        return orgCounterId;
    }

    public void setOrgCounterId(Long orgCounterId) {
        this.orgCounterId = orgCounterId;
    }

    public Organization getOrgCounter() {
        return orgCounter;
    }

    public void setOrgCounter(Organization orgCounter) {
        this.orgCounter = orgCounter;
    }

    public String getOrderCounterNo() {
        return orderCounterNo;
    }

    public void setOrderCounterNo(String orderCounterNo) {
        this.orderCounterNo = orderCounterNo == null ? null : orderCounterNo.trim();
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