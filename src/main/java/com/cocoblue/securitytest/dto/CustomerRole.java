package com.cocoblue.securitytest.dto;

import lombok.Data;

@Data
public class CustomerRole {
    private long roleId;
    private long cno;
    private String roleName;

    public CustomerRole() {
    }

    public CustomerRole(long cno, String roleName) {
        this.cno = cno;
        this.roleName = roleName;
    }
}
