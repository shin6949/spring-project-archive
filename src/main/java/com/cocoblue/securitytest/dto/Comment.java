package com.cocoblue.securitytest.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private long id;
    private long postId;
    private long writerId;
    private String writerName;
    private String content;
    private LocalDateTime writeTime;
    private String writeTimeString;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getWriterId() {
        return writerId;
    }

    public void setWriterId(long writerId) {
        this.writerId = writerId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getWriteTime() {
        return writeTime;
    }

    public String getWriteTimeString() {
        return writeTimeString;
    }

    public void setWriteTimeString(String writeTimeString) {
        this.writeTimeString = writeTimeString;
    }

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
        // View에서 LocalDateTime를 String로 변환하는데 애로사항이 있어 클래스에서 먼저 설정함.
        this.setWriteTimeString(writeTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", writerId=" + writerId +
                ", writerName='" + writerName + '\'' +
                ", content='" + content + '\'' +
                ", writeTime=" + writeTime +
                ", writeTimeString='" + writeTimeString + '\'' +
                '}';
    }
}
