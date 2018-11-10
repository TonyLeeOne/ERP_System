package com.tony.erp.domain;

public class Department {
    private String dId;

    private String dName;

    private String dMamager;

    private String dDuty;

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId == null ? null : dId.trim();
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

    public String getdMamager() {
        return dMamager;
    }

    public void setdMamager(String dMamager) {
        this.dMamager = dMamager == null ? null : dMamager.trim();
    }

    public String getdDuty() {
        return dDuty;
    }

    public void setdDuty(String dDuty) {
        this.dDuty = dDuty == null ? null : dDuty.trim();
    }
}