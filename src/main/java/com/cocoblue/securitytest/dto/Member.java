package com.cocoblue.securitytest.dto;

import java.time.LocalDate;

public class Member {
    private Long id;
    private String name;
    private String password;
    private String email;
    private LocalDate createDate;
    private LocalDate modifyDate;

    public Member() {
        createDate = LocalDate.now();
        modifyDate = LocalDate.now();
    }

    public Member(Long id, String name, String password, String email) {
        this();
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Member(String email) {
        this();
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }
}