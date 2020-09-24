package com.cocoblue.securitytest.service;

import java.util.List;
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
}
