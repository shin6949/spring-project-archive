package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRole {
    private long roleId;
    private long cno;
    private String roleName;

    public CustomerRole(long cno, String roleName) {
        this.cno = cno;
        this.roleName = roleName;
    }
}
