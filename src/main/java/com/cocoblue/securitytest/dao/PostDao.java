package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Post;

import java.util.List;

public class PostDao {
    private PostDao() { }
    private static PostDao dao = new PostDao();
    public static PostDao getInstance() { return dao; }
    public List<Post> selectAll() {
        List<Post> list = new ArrayList<Post>();
        return list;
    }
    public void updateview_number(Long number) {

    }
    public Post selectnumber(Long number) {
        return Post;
    }
    public void insert(Post post) {

    }
    public void update(Post post) {

    }
    public void delete(Long number) {

    }
}
