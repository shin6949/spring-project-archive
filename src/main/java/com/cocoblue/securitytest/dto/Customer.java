package com.cocoblue.securitytest.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class Customer {
    private long cno;
    private String id;
    private String password;
    private String name;
    // java.util.date는 문제가 많아서, java.time.LocalDate로 대체되고 있음.
    private LocalDate birthday;
    private Boolean gender;

    // Constructor for Spring Autowire
    public Customer() {    }
}