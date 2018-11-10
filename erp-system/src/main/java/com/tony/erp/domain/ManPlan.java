package com.tony.erp.domain;

public class ManPlan {
    private String mpId;

    private String mpSn;

    private String mpProCode;

    private String mpStartDate;

    private String mpEndDate;

    private Integer mpCount;

    private String mpOrderId;

    private String mpStatus;

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId == null ? null : mpId.trim();
    }

    public String getMpSn() {
        return mpSn;
    }

    public void setMpSn(String mpSn) {
        this.mpSn = mpSn == null ? null : mpSn.trim();
    }

    public String getMpProCode() {
        return mpProCode;
    }

    public void setMpProCode(String mpProCode) {
        this.mpProCode = mpProCode == null ? null : mpProCode.trim();
    }

    public String getMpStartDate() {
        return mpStartDate;
    }

    public void setMpStartDate(String mpStartDate) {
        this.mpStartDate = mpStartDate == null ? null : mpStartDate.trim();
    }

    public String getMpEndDate() {
        return mpEndDate;
    }

    public void setMpEndDate(String mpEndDate) {
        this.mpEndDate = mpEndDate == null ? null : mpEndDate.trim();
    }

    public Integer getMpCount() {
        return mpCount;
    }

    public void setMpCount(Integer mpCount) {
        this.mpCount = mpCount;
    }

    public String getMpOrderId() {
        return mpOrderId;
    }

    public void setMpOrderId(String mpOrderId) {
        this.mpOrderId = mpOrderId == null ? null : mpOrderId.trim();
    }

    public String getMpStatus() {
        return mpStatus;
    }

    public void setMpStatus(String mpStatus) {
        this.mpStatus = mpStatus == null ? null : mpStatus.trim();
    }
}