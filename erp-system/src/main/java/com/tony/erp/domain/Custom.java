package com.tony.erp.domain;

public class Custom {
    private String customId;

    private String customName;

    private String customAddress;

    private String customCode;

    private String customTel;

    private String customPublish;

    private String customFullname;

    private String customStatus;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName == null ? null : customName.trim();
    }

    public String getCustomAddress() {
        return customAddress;
    }

    public void setCustomAddress(String customAddress) {
        this.customAddress = customAddress == null ? null : customAddress.trim();
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode == null ? null : customCode.trim();
    }

    public String getCustomTel() {
        return customTel;
    }

    public void setCustomTel(String customTel) {
        this.customTel = customTel == null ? null : customTel.trim();
    }

    public String getCustomPublish() {
        return customPublish;
    }

    public void setCustomPublish(String customPublish) {
        this.customPublish = customPublish == null ? null : customPublish.trim();
    }

    public String getCustomFullname() {
        return customFullname;
    }

    public void setCustomFullname(String customFullname) {
        this.customFullname = customFullname == null ? null : customFullname.trim();
    }

    public String getCustomStatus() {
        return customStatus;
    }

    public void setCustomStatus(String customStatus) {
        this.customStatus = customStatus == null ? null : customStatus.trim();
    }
}