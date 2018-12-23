package com.tony.erp.domain;

import lombok.Data;

/**
 * @author jli2
 * @date  2018/11/12
 */
public class MaterialPurchase {

    private String mphId;

    private String mphName;

    private String mphSn;

    private Float mphPrice;

    private Integer mphCount;

    private String mphVendorId;

    private String mphPoId;

    private String mphSender;

    private String mphOperator;

    private String mphDate;

    private String mphStatus;

    private Vendor vendor=new Vendor();

    public String getMphId() {
        return mphId;
    }

    public void setMphId(String mphId) {
        this.mphId = mphId == null ? null : mphId.trim();
    }

    public String getMphName() {
        return mphName;
    }

    public void setMphName(String mphName) {
        this.mphName = mphName == null ? null : mphName.trim();
    }

    public String getMphSn() {
        return mphSn;
    }

    public void setMphSn(String mphSn) {
        this.mphSn = mphSn == null ? null : mphSn.trim();
    }

    public Float getMphPrice() {
        return mphPrice;
    }

    public void setMphPrice(Float mphPrice) {
        this.mphPrice = mphPrice;
    }

    public Integer getMphCount() {
        return mphCount;
    }

    public void setMphCount(Integer mphCount) {
        this.mphCount = mphCount;
    }

    public String getMphVendorId() {
        return mphVendorId;
    }

    public void setMphVendorId(String mphVendorId) {
        this.mphVendorId = mphVendorId == null ? null : mphVendorId.trim();
    }


    public String getMphSender() {
        return mphSender;
    }

    public void setMphSender(String mphSender) {
        this.mphSender = mphSender == null ? null : mphSender.trim();
    }

    public String getMphOperator() {
        return mphOperator;
    }

    public void setMphOperator(String mphOperator) {
        this.mphOperator = mphOperator == null ? null : mphOperator.trim();
    }

    public String getMphDate() {
        return mphDate;
    }

    public void setMphDate(String mphDate) {
        this.mphDate = mphDate == null ? null : mphDate.trim();
    }

    public String getMphPoId() {
        return mphPoId;
    }

    public void setMphPoId(String mphPoId) {
        this.mphPoId = mphPoId;
    }

    public String getMphStatus() {
        return mphStatus;
    }

    public void setMphStatus(String mphStatus) {
        this.mphStatus = mphStatus;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "MaterialPurchase{" +
                "mphId='" + mphId + '\'' +
                ", mphName='" + mphName + '\'' +
                ", mphSn='" + mphSn + '\'' +
                ", mphPrice=" + mphPrice +
                ", mphCount=" + mphCount +
                ", mphVendorId='" + mphVendorId + '\'' +
                ", mphPoId='" + mphPoId + '\'' +
                ", mphSender='" + mphSender + '\'' +
                ", mphOperator='" + mphOperator + '\'' +
                ", mphDate='" + mphDate + '\'' +
                ", mphStatus='" + mphStatus + '\'' +
                '}';
    }
}