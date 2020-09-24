package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Post;

import java.util.List;

public interface PostService {
    public List<Post> getPostsAll(String boardName);
}
