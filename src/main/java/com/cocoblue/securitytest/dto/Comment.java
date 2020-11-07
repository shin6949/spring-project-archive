package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@ToString
public class Comment {
    private long id;
    private long postId;
    private long writerId;
    private String writerName;
    private String content;
    private LocalDateTime writeTime;
    private String writeTimeString;

    public Comment() { }

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
        // View에서 LocalDateTime를 String로 변환하는데 애로사항이 있어 클래스에서 먼저 설정함.
        this.setWriteTimeString(writeTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
