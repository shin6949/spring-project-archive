package com.cocoblue.securitytest.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private long id;
    private String title;
    private String content;
    private long writerId;
    private LocalDateTime writeTime;
    private String writeTimeString;
    private long boardId;
    private long viewNumber;
    private String writerName;
    private String boardName;

    public Post() {
    }

    public Post(long id, String title, String content, long writerId, LocalDateTime writeTime, String writeTimeString, long boardId, long viewNumber, String writerName, String boardName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.writeTime = writeTime;
        this.writeTimeString = writeTimeString;
        this.boardId = boardId;
        this.viewNumber = viewNumber;
        this.writerName = writerName;
        this.boardName = boardName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getWriterId() {
        return writerId;
    }

    public void setWriterId(long writerId) {
        this.writerId = writerId;
    }

    public LocalDateTime getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
        // View에서 LocalDateTime를 String로 변환하는데 애로사항이 있어 클래스에서 먼저 설정함.
        this.setWriteTimeString(writeTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(long viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getWriteTimeString() {
        return writeTimeString;
    }

    public void setWriteTimeString(String writeTimeString) {
        this.writeTimeString = writeTimeString;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writerId=" + writerId +
                ", writeTime=" + writeTime +
                ", writeTimeString='" + writeTimeString + '\'' +
                ", boardId=" + boardId +
                ", viewNumber=" + viewNumber +
                ", writerName='" + writerName + '\'' +
                ", boardName='" + boardName + '\'' +
                '}';
    }
}
