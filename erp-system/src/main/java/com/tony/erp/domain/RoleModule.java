package com.tony.erp.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleModule {
    private String rid;

    private String mid;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }
}