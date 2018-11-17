package com.tony.erp.domain;
/**
 * @author jli2
 * @date  2018/11/12
 */
public class MaterialConsume {
    private String mcId;

    private String mcMpSn;

    private String mcMoSn;

    private String mcMSn;

    private Integer mcCountNeeded;

    private Integer mcCountIndeed;

    private String mcRequestor;

    private String mcOperator;

    private String mcDate;

    private String mcStatus;

    public String getMcId() {
        return mcId;
    }

    public void setMcId(String mcId) {
        this.mcId = mcId == null ? null : mcId.trim();
    }

    public String getMcMpSn() {
        return mcMpSn;
    }

    public void setMcMpSn(String mcMpSn) {
        this.mcMpSn = mcMpSn == null ? null : mcMpSn.trim();
    }

    public String getMcMoSn() {
        return mcMoSn;
    }

    public void setMcMoSn(String mcMoSn) {
        this.mcMoSn = mcMoSn == null ? null : mcMoSn.trim();
    }

    public String getMcMSn() {
        return mcMSn;
    }

    public void setMcMSn(String mcMSn) {
        this.mcMSn = mcMSn == null ? null : mcMSn.trim();
    }

    public Integer getMcCountNeeded() {
        return mcCountNeeded;
    }

    public void setMcCountNeeded(Integer mcCountNeeded) {
        this.mcCountNeeded = mcCountNeeded;
    }

    public Integer getMcCountIndeed() {
        return mcCountIndeed;
    }

    public void setMcCountIndeed(Integer mcCountIndeed) {
        this.mcCountIndeed = mcCountIndeed;
    }

    public String getMcRequestor() {
        return mcRequestor;
    }

    public void setMcRequestor(String mcRequestor) {
        this.mcRequestor = mcRequestor == null ? null : mcRequestor.trim();
    }

    public String getMcOperator() {
        return mcOperator;
    }

    public void setMcOperator(String mcOperator) {
        this.mcOperator = mcOperator == null ? null : mcOperator.trim();
    }

    public String getMcDate() {
        return mcDate;
    }

    public void setMcDate(String mcDate) {
        this.mcDate = mcDate == null ? null : mcDate.trim();
    }

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus == null ? null : mcStatus.trim();
    }
}