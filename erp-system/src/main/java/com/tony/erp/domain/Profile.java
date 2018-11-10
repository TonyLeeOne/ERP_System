package com.tony.erp.domain;

public class Profile {
    private String pid;

    private String pUid;

    private String pIndate;

    private String pFaredate;

    private String pMajor;

    private String pId;

    private String pSex;

    private String pEdu;

    private String pTel;

    private String pName;

    private String pWechat;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getpUid() {
        return pUid;
    }

    public void setpUid(String pUid) {
        this.pUid = pUid == null ? null : pUid.trim();
    }

    public String getpIndate() {
        return pIndate;
    }

    public void setpIndate(String pIndate) {
        this.pIndate = pIndate == null ? null : pIndate.trim();
    }

    public String getpFaredate() {
        return pFaredate;
    }

    public void setpFaredate(String pFaredate) {
        this.pFaredate = pFaredate == null ? null : pFaredate.trim();
    }

    public String getpMajor() {
        return pMajor;
    }

    public void setpMajor(String pMajor) {
        this.pMajor = pMajor == null ? null : pMajor.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getpSex() {
        return pSex;
    }

    public void setpSex(String pSex) {
        this.pSex = pSex == null ? null : pSex.trim();
    }

    public String getpEdu() {
        return pEdu;
    }

    public void setpEdu(String pEdu) {
        this.pEdu = pEdu == null ? null : pEdu.trim();
    }

    public String getpTel() {
        return pTel;
    }

    public void setpTel(String pTel) {
        this.pTel = pTel == null ? null : pTel.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getpWechat() {
        return pWechat;
    }

    public void setpWechat(String pWechat) {
        this.pWechat = pWechat == null ? null : pWechat.trim();
    }
}