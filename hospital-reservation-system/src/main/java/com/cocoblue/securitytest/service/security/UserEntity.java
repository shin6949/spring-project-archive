package com.cocoblue.securitytest.service.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private long cno;
    private String id;
    private String loginUserId;
    private String password;

    public String getLoginUserId() {
        return id;
    }
}