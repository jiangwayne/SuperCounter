package com.plus.server.model;

import java.util.Date;

public class ObjectParent {
    private Long id;

    private String name;

    private String qrCode;

    private Long orgId;

    private Integer type;

    private Integer length;

    private Integer width;

    private Integer height;

    private Integer lengthUp;

    private Integer widthUp;

    private Integer heightUp;

    private String picUrl;

    private String content;

    private Integer valid;

    private Date gmtCreate;

    private Date gmtModify;

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