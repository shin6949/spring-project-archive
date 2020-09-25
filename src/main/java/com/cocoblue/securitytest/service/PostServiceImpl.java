package com.cocoblue.securitytest.service;

import java.util.List;

import com.cocoblue.securitytest.dto.Comment;
import com.cocoblue.securitytest.dto.Post;
import com.cocoblue.securitytest.dao.PostDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    @Transactional
    public List<Post> getPostsAll(String boardName) {
        return postDao.getPostsAll(boardName);
    }

    @Override
    public Post getPost(String id) {
        return postDao.getPost(id);
    }

    @Override
    public List<Comment> getComments(String post_id) {
        return null;
    }

    @Override
    public Boolean writePost(Post post) {
        return postDao.writePost(post);
    }

    @Override
    public void increaseViewNum(String post_id) {
        postDao.increaseViewNum(post_id);
    }
}
