package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private long rno;
    private LocalDateTime reservationTime;
    private String reservationTimeString;
    private long doctorNo;
    private long cno;
    private Boolean confirmed;
    private String symptom;

    public Reservation(LocalDateTime reservationTime, String doctor, String symptom, long cno, Boolean confirmed) {
        this.reservationTime = reservationTime;
        this.doctorNo = Long.parseLong(doctor);
        this.symptom = symptom;
        this.confirmed = confirmed;
        this.cno = cno;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
        // View에서 LocalDateTime를 String로 변환하는데 애로사항이 있어 클래스에서 먼저 설정함.
        reservationTimeString = reservationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
