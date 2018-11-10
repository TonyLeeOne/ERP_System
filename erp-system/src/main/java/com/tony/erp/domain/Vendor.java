package com.tony.erp.domain;

public class Vendor {
    private String vId;

    private String vName;

    private String vAddress;

    private String vTel;

    private String vPublish;

    private String vFullname;

    private String vStatus;

    private String vNote;

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId == null ? null : vId.trim();
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName == null ? null : vName.trim();
    }

    public String getvAddress() {
        return vAddress;
    }

    public void setvAddress(String vAddress) {
        this.vAddress = vAddress == null ? null : vAddress.trim();
    }

    public String getvTel() {
        return vTel;
    }

    public void setvTel(String vTel) {
        this.vTel = vTel == null ? null : vTel.trim();
    }

    public String getvPublish() {
        return vPublish;
    }

    public void setvPublish(String vPublish) {
        this.vPublish = vPublish == null ? null : vPublish.trim();
    }

    public String getvFullname() {
        return vFullname;
    }

    public void setvFullname(String vFullname) {
        this.vFullname = vFullname == null ? null : vFullname.trim();
    }

    public String getvStatus() {
        return vStatus;
    }

    public void setvStatus(String vStatus) {
        this.vStatus = vStatus == null ? null : vStatus.trim();
    }

    public String getvNote() {
        return vNote;
    }

    public void setvNote(String vNote) {
        this.vNote = vNote == null ? null : vNote.trim();
    }
}