package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateDto {
    private LocalDate dateName;
    private String koreanDayOfTheWeek;

    public AvailableDateDto(LocalDate dateName) {
        this.dateName = dateName;
    }

    @Override
    public String toString() {
        return dateName.toString() + " (" + judgeKoreanDayOfTheWeek(dateName.getDayOfWeek().getValue()) + ")";
    }

    // LocalDateTime에서 Return하는 요일 값을 토대로 한글 요일 값을 리턴하는 함수
    private String judgeKoreanDayOfTheWeek(int dowInt) {
        switch (dowInt) {
            case 1:
                return "월";
            case 2:
                return "화";
            case 3:
                return "수";
            case 4:
                return "목";
            case 5:
                return "금";
            case 6:
                return "토";
            case 7:
                return "일";
            default:
                return "Unknown";
        }
    }
}
