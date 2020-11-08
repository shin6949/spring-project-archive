package com.cocoblue.securitytest.dao;

public class BoardDaoSqls {
    public static String SELECT_ALL = "SELECT b.post_id as post_id, b.title as title, b.cno as cno, b.write_time as write_time, b.view_number as view_number, c.name as writer_name FROM board as b JOIN customer as c ON b.cno = c.cno ORDER BY b.write_time DESC";

    public static String SELECT_BY_POST_ID = "SELECT b.post_id as post_id, b.title as title, b.content as content, b.cno as cno, b.write_time as write_time, b.view_number as view_number, c.name as writer_name FROM board as b JOIN customer as c ON b.cno = c.cno WHERE b.post_id = :postId";

    public static String UPDATE_VIEW_NUMBER = "UPDATE board SET view_number = view_number + 1 WHERE post_id = :postId";

    public static String SELECT_POSTS_COUNT = "SELECT COUNT(*) as post_count FROM board";

    public static String SELECT_POSTS_BY_PAGE = "SELECT b.post_id as post_id, b.title as title, b.cno as cno, b.write_time as write_time, b.view_number as view_number, c.name as writer_name FROM board as b JOIN customer as c ON b.cno = c.cno ORDER BY b.write_time DESC LIMIT :start, :end";

    public static String SELECT_POSTS_BY_KEYWORD = "SELECT b.post_id as post_id, b.title as title, b.cno as cno, b.write_time as write_time, b.view_number as view_number, c.name as writer_name FROM board as b JOIN customer as c ON b.cno = c.cno WHERE b.content LIKE :keyword OR b.title LIKE :keyword ORDER BY b.write_time DESC LIMIT :start, :end";

    public static String SELECT_COUNT_BY_KEYWORD = "SELECT count(*) as post_count FROM board as b WHERE b.title LIKE :keyword OR b.content LIKE :keyword";

    public static String DELETE_BY_POST_ID = "DELETE FROM board WHERE post_id = :postId";

    public static String UPDATE_BY_POST_ID = "UPDATE board SET content = :content, title = :title WHERE post_id = :postId";
}
