package com.tony.erp.domain;

import java.util.HashSet;
import java.util.Set;

public class PurchaseOrder {

    private String poId;

    private String poCdate;

    private String poOno;

    private Integer poCount;

    private String poBcode;

    private String poVerifier;

    private String poDate;

    private String poStatus;

    private Set<MaterialPurchase> purchases=new HashSet<>();

    public String getPoId() {
        return poId;
    }

    public void setPoId(String poId) {
        this.poId = poId == null ? null : poId.trim();
    }

    public String getPoCdate() {
        return poCdate;
    }

    public void setPoCdate(String poCdate) {
        this.poCdate = poCdate == null ? null : poCdate.trim();
    }

    public String getPoOno() {
        return poOno;
    }

    public void setPoOno(String poOno) {
        this.poOno = poOno == null ? null : poOno.trim();
    }

    public Integer getPoCount() {
        return poCount;
    }

    public void setPoCount(Integer poCount) {
        this.poCount = poCount;
    }

    public String getPoBcode() {
        return poBcode;
    }

    public void setPoBcode(String poBcode) {
        this.poBcode = poBcode == null ? null : poBcode.trim();
    }

    public String getPoVerifier() {
        return poVerifier;
    }

    public void setPoVerifier(String poVerifier) {
        this.poVerifier = poVerifier == null ? null : poVerifier.trim();
    }

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate == null ? null : poDate.trim();
    }

    public String getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus == null ? null : poStatus.trim();
    }

    public Set<MaterialPurchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<MaterialPurchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "poId='" + poId + '\'' +
                ", poCdate='" + poCdate + '\'' +
                ", poOno='" + poOno + '\'' +
                ", poCount=" + poCount +
                ", poBcode='" + poBcode + '\'' +
                ", poVerifier='" + poVerifier + '\'' +
                ", poDate='" + poDate + '\'' +
                ", poStatus='" + poStatus + '\'' +
                ", purchases=" + purchases +
                '}';
    }
}