package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationForm {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime reservationOnlyTime;
    private long doctorNo;
    private String symptom;
}
