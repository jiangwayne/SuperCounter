package com.plus.server.model;

import java.util.Date;

public class OrderSupplier {
    private Long id;

    private Long orderId;

    private Long orgSupplierId;
    private Organization orgSupplier;

    private String orderSupplierNo;

    private String comment;

    private Integer valid;

    private Date gmtCreate;

    private Date gmtModify;

    public Organization getOrgSupplier() {
		return orgSupplier;
	}

	public void setOrgSupplier(Organization orgSupplier) {
		this.orgSupplier = orgSupplier;
	}

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

    public Long getOrgSupplierId() {
        return orgSupplierId;
    }

    public void setOrgSupplierId(Long orgSupplierId) {
        this.orgSupplierId = orgSupplierId;
    }

    public String getOrderSupplierNo() {
        return orderSupplierNo;
    }

    public void setOrderSupplierNo(String orderSupplierNo) {
        this.orderSupplierNo = orderSupplierNo == null ? null : orderSupplierNo.trim();
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