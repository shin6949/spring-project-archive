package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationView {
    private long rno;
    private long cno;
    private LocalDateTime reservationTime;
    private String reservationTimeString;
    private Boolean confirmed;
    private String symptom;
    private String department;
    private String doctor;

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
        // View에서 LocalDateTime를 String로 변환하는데 애로사항이 있어 클래스에서 먼저 설정함.
        reservationTimeString = reservationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public boolean isCancelable(){
        LocalDateTime currentTime = LocalDateTime.now();
        if(confirmed){
            return currentTime.isBefore(reservationTime);
        }
        return false;
    }
}
