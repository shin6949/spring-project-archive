package com.cocoblue.securitytest.dao;

public class CommentDaoSqls {
    public static String SELECT_COUNT_BY_POST_ID = "SELECT count(*) as comment_count FROM comment WHERE post_id = :postId GROUP BY post_id";
    public static String SELECT_ALL_BY_POST_ID = "SELECT c.content as content, c.write_time as write_time, c.writer_id as writer_id, m.name as writer_name FROM comment as c\n" +
            "join member as m ON c.writer_id = m.id\n" +
            "WHERE c.post_id = :postId";
}
