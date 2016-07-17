package com.plus.server.model;

import java.util.Date;

public class OrderSupplierDetail {
    private Long id;

    private Long orderId;

    private Long orderSupplierId;

    private Long objParentId;

    private Integer objParentCount;

    private Integer backupCount;

    private Date inStockTime;

    private Date outStockTime;

    private Integer priceCny;

    private Integer priceUsd;

    private Integer priceEur;

    private Integer unitPriceCny;

    private Integer unitPriceUsd;

    private Integer unitPriceEur;

    private Integer finalPriceCny;

    private Integer finalPriceUsd;

    private Integer finalPriceEur;

    private Integer finalUnitPriceCny;

    private Integer finalUnitPriceUsd;

    private Integer finalUnitPriceEur;

    private String poNumber;

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

    public Long getOrderSupplierId() {
        return orderSupplierId;
    }

    public void setOrderSupplierId(Long orderSupplierId) {
        this.orderSupplierId = orderSupplierId;
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

    public Integer getBackupCount() {
        return backupCount;
    }

    public void setBackupCount(Integer backupCount) {
        this.backupCount = backupCount;
    }

    public Date getInStockTime() {
        return inStockTime;
    }

    public void setInStockTime(Date inStockTime) {
        this.inStockTime = inStockTime;
    }

    public Date getOutStockTime() {
        return outStockTime;
    }

    public void setOutStockTime(Date outStockTime) {
        this.outStockTime = outStockTime;
    }

    public Integer getPriceCny() {
        return priceCny;
    }

    public void setPriceCny(Integer priceCny) {
        this.priceCny = priceCny;
    }

    public Integer getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Integer priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Integer getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(Integer priceEur) {
        this.priceEur = priceEur;
    }

    public Integer getUnitPriceCny() {
        return unitPriceCny;
    }

    public void setUnitPriceCny(Integer unitPriceCny) {
        this.unitPriceCny = unitPriceCny;
    }

    public Integer getUnitPriceUsd() {
        return unitPriceUsd;
    }

    public void setUnitPriceUsd(Integer unitPriceUsd) {
        this.unitPriceUsd = unitPriceUsd;
    }

    public Integer getUnitPriceEur() {
        return unitPriceEur;
    }

    public void setUnitPriceEur(Integer unitPriceEur) {
        this.unitPriceEur = unitPriceEur;
    }

    public Integer getFinalPriceCny() {
        return finalPriceCny;
    }

    public void setFinalPriceCny(Integer finalPriceCny) {
        this.finalPriceCny = finalPriceCny;
    }

    public Integer getFinalPriceUsd() {
        return finalPriceUsd;
    }

    public void setFinalPriceUsd(Integer finalPriceUsd) {
        this.finalPriceUsd = finalPriceUsd;
    }

    public Integer getFinalPriceEur() {
        return finalPriceEur;
    }

    public void setFinalPriceEur(Integer finalPriceEur) {
        this.finalPriceEur = finalPriceEur;
    }

    public Integer getFinalUnitPriceCny() {
        return finalUnitPriceCny;
    }

    public void setFinalUnitPriceCny(Integer finalUnitPriceCny) {
        this.finalUnitPriceCny = finalUnitPriceCny;
    }

    public Integer getFinalUnitPriceUsd() {
        return finalUnitPriceUsd;
    }

    public void setFinalUnitPriceUsd(Integer finalUnitPriceUsd) {
        this.finalUnitPriceUsd = finalUnitPriceUsd;
    }

    public Integer getFinalUnitPriceEur() {
        return finalUnitPriceEur;
    }

    public void setFinalUnitPriceEur(Integer finalUnitPriceEur) {
        this.finalUnitPriceEur = finalUnitPriceEur;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber == null ? null : poNumber.trim();
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