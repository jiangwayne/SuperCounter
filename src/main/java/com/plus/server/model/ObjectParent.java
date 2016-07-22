package com.plus.server.model;

import java.util.Date;
import java.util.List;

public class ObjectParent {
    private Long id;

    private String name;

    private String qrCode;

    private Long orgId;

    private Long brandId;

    public Long getCounterId() {
        return counterId;
    }

    public void setCounterId(Long counterId) {
        this.counterId = counterId;
    }

    private Long counterId;

    private String objParentNo;


    private Integer type;
    private List<Integer> typeList;

    private Integer length;

    private Integer width;

    private Integer height;

    private Integer lengthUp;

    private Integer widthUp;

    private Integer heightUp;

    private String picUrl;

    private String material;

    private String content;

    private Integer valid;

    private String remark;

    private Date gmtCreate;

    private Date gmtModify;

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }

    private String siteNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLengthUp() {
        return lengthUp;
    }

    public void setLengthUp(Integer lengthUp) {
        this.lengthUp = lengthUp;
    }

    public Integer getWidthUp() {
        return widthUp;
    }

    public void setWidthUp(Integer widthUp) {
        this.widthUp = widthUp;
    }

    public Integer getHeightUp() {
        return heightUp;
    }

    public void setHeightUp(Integer heightUp) {
        this.heightUp = heightUp;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getObjParentNo() {
        return objParentNo;
    }

    public void setObjParentNo(String objParentNo) {
        this.objParentNo = objParentNo;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

	public List<Integer> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Integer> typeList) {
		this.typeList = typeList;
	}
}