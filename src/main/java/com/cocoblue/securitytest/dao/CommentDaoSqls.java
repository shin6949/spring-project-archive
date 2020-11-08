package com.cocoblue.securitytest.dao;

public class CommentDaoSqls {
    public static String SELECT_COUNT_BY_POST_ID = "SELECT count(*) as comment_count FROM comment WHERE post_id = :postId";
    public static String SELECT_ALL_BY_POST_ID = "SELECT comm.comment_id as comment_id, comm.content as content, comm.write_time as write_time, comm.writer_id as writer_id, cust.name as writer_name FROM comment as comm join customer as cust ON comm.writer_id = cust.cno WHERE comm.post_id = :postId";
    public static String DELETE_BY_COMMENT_ID = "DELETE FROM comment WHERE comment_id = :commentId";
    public static String SELECT_BY_COMMENT_ID = "SELECT * FROM comment WHERE comment_id = :commentId";
}
