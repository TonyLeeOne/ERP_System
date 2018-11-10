package com.tony.erp.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRole {
    private String uid;

    private String rid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }
}