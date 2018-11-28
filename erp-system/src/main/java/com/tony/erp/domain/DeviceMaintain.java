package com.tony.erp.domain;
/**
 * @author jli2
 * @date  2018/11/12
 */
public class DeviceMaintain {
    private String hisId;

    private String hisDeviceCode;

    private String hisDate;

    private String hisOperator;

    private String hisResult;

    private String hisNote;

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId == null ? null : hisId.trim();
    }

    public String getHisDeviceCode() {
        return hisDeviceCode;
    }

    public void setHisDeviceCode(String hisDeviceCode) {
        this.hisDeviceCode = hisDeviceCode == null ? null : hisDeviceCode.trim();
    }

    public String getHisDate() {
        return hisDate;
    }

    public void setHisDate(String hisDate) {
        this.hisDate = hisDate == null ? null : hisDate.trim();
    }

    public String getHisOperator() {
        return hisOperator;
    }

    public void setHisOperator(String hisOperator) {
        this.hisOperator = hisOperator == null ? null : hisOperator.trim();
    }

    public String getHisResult() {
        return hisResult;
    }

    public void setHisResult(String hisResult) {
        this.hisResult = hisResult == null ? null : hisResult.trim();
    }

    public String getHisNote() {
        return hisNote;
    }

    public void setHisNote(String hisNote) {
        this.hisNote = hisNote == null ? null : hisNote.trim();
    }
}