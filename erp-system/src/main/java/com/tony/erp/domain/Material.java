package com.tony.erp.domain;
/**
 * @author jli2
 * @date  2018/11/12
 */
public class Material {

    private String mId;

    private String mName;

    private String mSn;

    private Integer mCount;

    private String mUnit;

    private String mNote;

    private String mStatus;

    private Float mPrice;

    private String mLocked;

    public Float getmPrice() {
        return mPrice;
    }

    public void setmPrice(Float mPrice) {
        this.mPrice = mPrice;
    }

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

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

    public String getmLocked() {
        return mLocked;
    }

    public void setmLocked(String mLocked) {
        this.mLocked = mLocked;
    }

    @Override
    public String toString() {
        return "Material{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mSn='" + mSn + '\'' +
                ", mCount=" + mCount +
                ", mUnit='" + mUnit + '\'' +
                ", mNote='" + mNote + '\'' +
                ", mStatus='" + mStatus + '\'' +
                ", mPrice=" + mPrice +
                ", mLocked='" + mLocked + '\'' +
                '}';
    }
}
