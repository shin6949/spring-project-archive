package com.cocoblue.securitytest.service.security;

public class UserEntity {
    private long id;
    private String loginUserId;
    private String password;

    public UserEntity(long id, String loginUserId, String password) {
        this.id = id;
        this.loginUserId = loginUserId;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "password" + this.getPassword();
    }
}