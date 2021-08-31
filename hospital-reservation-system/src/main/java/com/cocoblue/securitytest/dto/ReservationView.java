package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationView {
    private long rno;
    private long cno;
    private LocalDateTime reservationTime;
    private Boolean confirmed;
    private String symptom;
    private String department;
    private String doctor;

    public void setConfirmed(Boolean confirmed) {
        if(confirmed == null) {
            this.confirmed = false;
        } else {
            this.confirmed = confirmed;
        }
    }

    public String getReservationTimeString() {
        return reservationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public boolean isCancelable() {
        LocalDateTime currentTime = LocalDateTime.now();
        if(confirmed){
            return currentTime.isBefore(reservationTime);
        }
        return false;
    }
}
