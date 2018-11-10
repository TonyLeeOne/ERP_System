package com.tony.erp.domain;

public class ManOrder {
    private String moId;

    private String moSn;

    private String moMpSn;

    private String moStartDate;

    private String moEndDate;

    private Integer moCount;

    private Integer moWaitCount;

    private String moStatus;

    public String getMoId() {
        return moId;
    }

    public void setMoId(String moId) {
        this.moId = moId == null ? null : moId.trim();
    }

    public String getMoSn() {
        return moSn;
    }

    public void setMoSn(String moSn) {
        this.moSn = moSn == null ? null : moSn.trim();
    }

    public String getMoMpSn() {
        return moMpSn;
    }

    public void setMoMpSn(String moMpSn) {
        this.moMpSn = moMpSn == null ? null : moMpSn.trim();
    }

    public String getMoStartDate() {
        return moStartDate;
    }

    public void setMoStartDate(String moStartDate) {
        this.moStartDate = moStartDate == null ? null : moStartDate.trim();
    }

    public String getMoEndDate() {
        return moEndDate;
    }

    public void setMoEndDate(String moEndDate) {
        this.moEndDate = moEndDate == null ? null : moEndDate.trim();
    }

    public Integer getMoCount() {
        return moCount;
    }

    public void setMoCount(Integer moCount) {
        this.moCount = moCount;
    }

    public Integer getMoWaitCount() {
        return moWaitCount;
    }

    public void setMoWaitCount(Integer moWaitCount) {
        this.moWaitCount = moWaitCount;
    }

    public String getMoStatus() {
        return moStatus;
    }

    public void setMoStatus(String moStatus) {
        this.moStatus = moStatus == null ? null : moStatus.trim();
    }
}