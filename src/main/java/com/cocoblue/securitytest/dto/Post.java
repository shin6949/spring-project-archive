package com.cocoblue.securitytest.dto;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private LocalDateTime writeTime;
    private Long boardId;
    private Long viewNumber;
    private String writerName;
    private String boardName;

    // Default Constructor
    public Post() {
    }

    // Original Post Constructor
    public Post(Long id, String title, String content, Long writerId, LocalDateTime writeTime, Long boardId, Long viewNumber) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.writeTime = writeTime;
        this.boardId = boardId;
        this.viewNumber = viewNumber;
    }

    // Post Constructor for Post List
    public Post(Long id, String title, String content, Long writerId, LocalDateTime writeTime, Long boardId, Long viewNumber, String writerName, String boardName) {
        this(id, title, content, writerId, writeTime, boardId, viewNumber);
        this.writerName = writerName;
        this.boardName = boardName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public LocalDateTime getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Long viewNumber) {
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writerId=" + writerId +
                ", writeTime=" + writeTime +
                ", boardId=" + boardId +
                ", viewNumber=" + viewNumber +
                ", writerName='" + writerName + '\'' +
                ", boardName='" + boardName + '\'' +
                '}';
    }

}
