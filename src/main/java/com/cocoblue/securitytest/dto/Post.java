package com.cocoblue.securitytest.dto;

import java.util.Date;

public class Post {
    private Long number;
    private String title;
    private String content;
    private Long writer;
    private Date write_time;
    private Long board_id;
    private Long view_number;

    public Long getNumber(){
        return number;
    }
    public void setNumber(Long number){
        this.number=number;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }
    public Long getWriter(){
        return writer;
    }
    public void setWriter(Long writer){
        this.writer=writer;
    }
    public Date getWrite_time(){
        return write_time;
    }
    public void setWrite_time(Date write_time){
        this.write_time=write_time;
    }
    public Long getBoard_id(){
        return board_id;
    }
    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }
    public Long getView_number(){
        return view_number;
    }
    public void setView_number(Long view_number) {
        this.view_number = view_number;
    }
    public String toString(){
        return "Post [number=" + number + ", title=" + title + ", content=" + content +
                ", writer=" + writer + ", writer_time" + write_time + ", view_number" + view_number + "]";
    }
}
