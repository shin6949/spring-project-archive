package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
    private long postId;
    private String title;
    private String content;
    private long cno;
    private LocalDateTime writeTime;
    private String writeTimeString;
    private long viewNumber;
    private String writerName;

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
        // View에서 LocalDateTime를 String로 변환하는데 애로사항이 있어 클래스에서 먼저 설정함.
        writeTimeString = writeTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
