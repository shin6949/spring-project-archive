package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private long cno;
    // E-mail 형태 ID 채택
    private String id;
    private String password;
    private String name;
    // java.util.date는 문제가 많아서, java.time.LocalDate로 대체되고 있음.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Boolean gender;
}