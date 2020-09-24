package com.cocoblue.securitytest.dto;

import java.time.LocalDateTime;

public class Comment {
    private long id;
    private long post_id;
    private long write_id;
    private String content;
    LocalDateTime writeTime;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post_id=" + post_id +
                ", write_id=" + write_id +
                ", content='" + content + '\'' +
                ", writeTime=" + writeTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getWrite_id() {
        return write_id;
    }

    public void setWrite_id(long write_id) {
        this.write_id = write_id;
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

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }
}
