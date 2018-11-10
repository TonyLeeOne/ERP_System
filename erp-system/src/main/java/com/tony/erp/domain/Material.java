package com.tony.erp.domain;

public class Material {
    private String mId;

    private String mName;

    private String mSn;

    private Integer mCount;

    private String mNote;

    private String mStatus;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId == null ? null : mId.trim();
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName == null ? null : mName.trim();
    }

    public String getmSn() {
        return mSn;
    }

    public void setmSn(String mSn) {
        this.mSn = mSn == null ? null : mSn.trim();
    }

    public Integer getmCount() {
        return mCount;
    }

    public void setmCount(Integer mCount) {
        this.mCount = mCount;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote == null ? null : mNote.trim();
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus == null ? null : mStatus.trim();
    }
}