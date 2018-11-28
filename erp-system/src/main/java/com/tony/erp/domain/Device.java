package com.tony.erp.domain;
/**
 * @author jli2
 * @date  2018/11/12
 */
public class Device {
    private String deviceId;

    private String deviceName;

    private String devicePurDate;

    private Integer devicePrice;

    private String deviceCode;

    private String deviceVendor;

    private String deviceVendorTel;

    private String deviceUsedPeriod;

    private String deviceNote;

    private String deviceStatus;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDevicePurDate() {
        return devicePurDate;
    }

    public void setDevicePurDate(String devicePurDate) {
        this.devicePurDate = devicePurDate == null ? null : devicePurDate.trim();
    }

    public Integer getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(Integer devicePrice) {
        this.devicePrice = devicePrice;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getDeviceVendor() {
        return deviceVendor;
    }

    public void setDeviceVendor(String deviceVendor) {
        this.deviceVendor = deviceVendor == null ? null : deviceVendor.trim();
    }

    public String getDeviceVendorTel() {
        return deviceVendorTel;
    }

    public void setDeviceVendorTel(String deviceVendorTel) {
        this.deviceVendorTel = deviceVendorTel == null ? null : deviceVendorTel.trim();
    }

    public String getDeviceUsedPeriod() {
        return deviceUsedPeriod;
    }

    public void setDeviceUsedPeriod(String deviceUsedPeriod) {
        this.deviceUsedPeriod = deviceUsedPeriod == null ? null : deviceUsedPeriod.trim();
    }

    public String getDeviceNote() {
        return deviceNote;
    }

    public void setDeviceNote(String deviceNote) {
        this.deviceNote = deviceNote == null ? null : deviceNote.trim();
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus == null ? null : deviceStatus.trim();
    }
}